Jeancarlos Almendarez

CSC 311

# Campus Lost and Found

This is a Spring Boot app for our CSC 311 class. People can report items they found on campus and see a list of items that have not been claimed yet.

You need an account to use the app. You can make one on the register page and then sign in.

## What I added

- A user package with AppUser, AppUserRepository and DatabaseUserDetailsService
- Passwords get saved as BCrypt hashes so they are never stored as plain text
- A register page that checks the username and password and shows an error if something is wrong
- A sign in page with a link to the register page
- The main page now shows who is signed in and has a sign out button
- The report item form sends the CSRF token so it still works with Spring Security

## How to run it

1. Open the project in IntelliJ
2. Reload Maven so it downloads everything
3. Run LostfoundApplication
4. Go to http://localhost:8080 in your browser
5. Click Register to make an account and then sign in

## Built with

Java 21, Spring Boot, Spring Security, Thymeleaf, Spring Data JPA and an H2 database
