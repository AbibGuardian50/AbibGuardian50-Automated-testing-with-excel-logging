import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testng.keyword.TestNGBuiltinKeywords as TestNGKW
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys
import org.apache.poi.xssf.usermodel.XSSFWorkbook as XSSFWorkbook
import org.apache.poi.ss.usermodel.*
import java.io.*

WebUI.openBrowser('')

TestData loginData = findTestData('Login user')

// Create a new workbook and sheet to store login results
Workbook workbook = new XSSFWorkbook()

Sheet sheet = workbook.createSheet('Login Result')

// Create header row
Row headerRow = sheet.createRow(0)

headerRow.createCell(0).setCellValue('Username')

headerRow.createCell(1).setCellValue('Password')

headerRow.createCell(2).setCellValue('Result')

for (int i = 1; i <= loginData.getRowNumbers(); i++) {
    WebUI.navigateToUrl('https://practicetestautomation.com/practice-test-login/')

     username = loginData.getValue('Username', i)
     password = loginData.getValue('Password', i)

    WebUI.setText(findTestObject('Page_Test Login  Practice Test Automation/input_Username_username'), username)
    WebUI.setText(findTestObject('Page_Test Login  Practice Test Automation/Page_Test Login  Practice Test Automation/input_Password_password'), password)

    // Save URL before login
    String beforeLoginUrl = WebUI.getUrl()

    WebUI.click(findTestObject('Page_Test Login  Practice Test Automation/button_Submit'))

    WebUI.delay(2)

    // Store URL after click submit
    String afterLoginUrl = WebUI.getUrl()

    boolean logininvalid = false
    boolean loginsuccess = false

    if (beforeLoginUrl != afterLoginUrl) {
        // The page changes -> login is successful
        loginsuccess = WebUI.verifyElementPresent(findTestObject('Page_Test Login  Practice Test Automation/Page_Logged In Successfully  Practice Test Automation/strong_Congratulations student. You successfully logged in'), 
            5, FailureHandling.OPTIONAL)
    } else {
        // The page does not change -> possibly login failed
        logininvalid = WebUI.verifyElementPresent(findTestObject('Page_Test Login  Practice Test Automation/Page_Test Login  Practice Test Automation/div_Your username is invalid'), 
            5, FailureHandling.OPTIONAL)
    }

    // Print result
    if (loginsuccess) {
        println("✅ Login success for user: $username")
    } else if (logininvalid) {
        println("❌ Login fail for user: $username")
    } else {
        println("⚠️ Status login unknown for user: $username")
    }

    // Write to Excel
    Row row = sheet.createRow(i)
    row.createCell(0).setCellValue(username)
    row.createCell(1).setCellValue(password)
    row.createCell(2).setCellValue(
        loginsuccess ? 'Login Successfull' : (logininvalid ? 'Login Failed' : 'Unknown Result')
    )
}

WebUI.closeBrowser()

// Save the Excel result file
FileOutputStream outFile = new FileOutputStream(new File('LoginResults.xlsx'))

workbook.write(outFile)

outFile.close()

println('✅ The login results have been saved in LoginResults.xlsx.')

