

📄 Project Overview

This project demonstrates a data-driven login automation test using Katalon Studio. It validates login functionality on [Practice Test Automation Site](https://practicetestautomation.com/practice-test-login/) by fetching user credentials from an Excel file, performing login attempts, verifying the result (success or failure), and writing the outcome back to a new Excel file.

🚀 Features

    ✅ Data-driven testing using Katalon’s built-in Excel integration

    🔁 Automatic iteration over multiple username/password combinations

    🧠 Intelligent result detection based on page URL and element verification

    📤 Result logging into a structured Excel file (LoginResults.xlsx)

    📚 Clear logging for success, failure, or unknown results

🧪 Test Flow

    1. Open Browser and Navigate to the login page.

    2. Fetch Username & Password from Excel test data.

    3. Submit Login Form using Katalon WebUI commands.

    4. Check Login Outcome:

        If page URL changes → success → check for success message.

        If page URL stays the same → likely failure → check for error message.

    5. Write Result to Excel: Successful / Failed / Unknown.

    6. Close Browser.

    7. Export Results to LoginResults.xlsx.


🚀 How to Run the Project

    1. Clone the repository
    
    2. Open Katalon Studio, go to File > Open Project, and select the cloned folder.

    3. Open the test case: LoginTestWithExcelLogging, then click ▶️ to execute.

    4. The test results will be saved automatically in:
        /project-root/LoginResults.xlsx


   
📸 Sample Output

![image](https://github.com/user-attachments/assets/b91ff513-fc47-4805-9127-51291dbe5168)


