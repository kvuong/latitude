# Latitude Stock Prices
This is an application that will take an array of stock prices from an input file and determine the best profit that could be made from 1 purchase and 1 sale of 1 Latitude Financial stock. 

The application uses an algorithm that loops through stock prices comparing the gain between the lowest price thus far against prices thereafter. This approach has a complixity of O(n).

## Assumptions & constraints
The following assumptions are made:
* Each stock price listed in the input file is on a separate line and is numeric.
* Time interval between each stock price is 1 minute.
* You must buy before you sell. You may not buy and sell in the same time step (at least 1 minute must pass).
* Trading commences at 10am local time and closes at 4pm.

## Pre-requisite
In order to run the application, the system will require:
* Java 8

# Usuage
## Build
The project uses Gradle and can be built using Gradle wrapper. To build the project follow these steps:
1. Open command prompt or terminal and browse to project directory.
2. Execute:
```
./gradlew build
```
This will build an executable jar (stock-price-1.0-0.jar) and placed in /build/libs relative to project directory.
## Run
After the project is built, to run the application follow these steps:
1. Open a new terminal window/command prompt.
2. Navigate to /build/libs directory relative to where the project directory resides. E.g. if project directory is "proj_dir" then navigate to ./proj_dir/build/libs
3. Execute the following command:
```
java -jar stock-price-1.0-0.jar <input_file>
```
* <input_file> is the location of the input file that contains the stock prices. 
### Example
Included with the project is a sample file (stock_prices_sample.csv) of 360 stock prices that could be used as an example. To run the application with the sample file simply execute:
```
java -jar stock-price-1.0-0.jar /latitude/stock_prices_sample.csv
```
\* It is assumed that the project directory is under the root directory as latitude.