# AuthenticationDB

a basic Java authentication system backed by MySQL. handles user registration, login, and session tokens.

## what it does

- register users with bcrypt-hashed passwords
- login and get a session token (valid 7 days)
- validate a token to see who's logged in
- logout by deleting the token
- auto-cleans up expired sessions

## stack

- Java 23, Maven
- MySQL
- jBCrypt for password hashing
- JDBC for database access

## setup

1. create the database using `schema.sql`
2. update credentials in `Database.java` to match your local MySQL setup
3. build with `mvn package` and run `Main.java`

## usage

runs as a CLI, just pick from the menu:

```
0) Exit
1) Register
2) Login
3) Who am I
4) Logout
```
