import csv

with open("people.csv", 'r') as read_file, open("search_examples.csv", 'w') as write_file:
    csv_reader = csv.reader(read_file)
    csv_writer = csv.writer(write_file)
    index = 1
    for row in csv_reader:
        try:
            first_name = row[0].split()[0][0:2]
            second_name = row[0].split()[1][0:2]
            request = {
                "first_name": first_name,
                "second_name": second_name,
                "birthdate": "2017-02-01",
                "biography": "bio",
                "city": row[2],
                "password": "pswd"
            }
            print(request)
            if index % 100 == 0:
                new_row = [first_name, second_name]
                csv_writer.writerow(new_row)
            index = index + 1
        except Exception as e:
            print('Exception = ' + e)