----------------------------
		SETUP
----------------------------
1. Clone this Git Repository to your local storage
2. Open Eclipse(or any other IDE)
3. Select Import projects
4. Select Maven
5. Select Existing Maven Projects
6. Browse for the extracted folder
7. Click Open
8. Click Finish
9. Open 'ClubApiApplication.java' file in 'come.abcfitness.clubapi' package
10. Right click and run it as Java Application
 -----> The Application will run on default port localhost:8080
 
 
-------------------------------
 		API endpoints
------------------------------

You can use any REST client like Postman or cURL. Below are sample API endpoints:

1. Create a Class

Mapping- POST http://localhost:8080/api/classes
Request Body: json
{
  "name": "Pilates",
  "startDate": "2025-01-22",
  "endDate": "2025-01-28",
  "startTime": "14:00",
  "duration": 60,
  "capacity": 10
}


2. Get All Classes

Mapping- GET http://localhost:8080/api/classes


3. Create a Booking

Mapping- POST http://localhost:8080/api/bookings,
Request Body: json
{
  "memberName": "John Doe",
  "className": "Pilates",
  "participationDate": "2025-01-25"
}


4. Search Bookings

Mapping- GET http://localhost:8080/api/bookings?memberName=John Doe&startDate=2025-01-21&endDate=2025-01-31
**Note: All parameters are optional
