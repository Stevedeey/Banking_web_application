# Digicore Online Banking App  

This is a simple banking web application for a bank. 

This application allows the following endpoints:

1. GET \account_info\{accountNumber} : User can query their account balance by account number and account password.

2. GET\account_statement\{accountNumber}: User can get the statement of their account.

3. POST \deposit: User can deposit to any account number with any amount below #1,000,000:00 and this will impact the account in real time

4. POST\withdrawal:User can withdraw if has at least #500 after the withdrawn amount is deducted from current balance

5.POST\create_account: User can create an account.