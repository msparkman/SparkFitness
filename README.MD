# SparkFitness
A simple application for storing and viewing workouts.

You'll need to create a mysql database and point the backend towards it. I went ahead and inserted some test data.

1. Build and run the app. Go the root directory and type:
	gradle build bootRun
	Or just run the app if it has already been built by doing:
	gradle bootRun
2. Go to localhost:8080/ to test that the server is up.
3. Assuming you inserted some test data in the database with a userId of 1, you can go to http://localhost:8080/getLastRoutine/1
	to return that data in JSON form. 