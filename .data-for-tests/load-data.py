import json
import csv
import subprocess

with open("./people.csv", 'r') as file:
    csv_reader = csv.reader(file)
    for row in csv_reader:
        try:
            first_name = row[0].split()[0]
            second_name = row[0].split()[1]
            request = {
                "first_name": first_name,
                "second_name": second_name,
                "birthdate": "2017-02-01",
                "biography": "bio",
                "city": row[2],
                "password": "pswd"
            }
            output = subprocess.check_output(['curl', '-X', 'POST', '--header', 'Content-Type: application/json', '-d', json.dumps(request), 'http://localhost:8080/user/register'])
            print(output)
            # print(request)
        except Exception as e:
            print('Exception = ' + e)