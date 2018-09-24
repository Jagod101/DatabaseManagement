# Zach Jagoda
# Student ID: 2274813
# Student Email: jagod101@mail.chapman.edu
# CPSC408 Database Management
# Assignment 2: SQLite Lab

import sqlite3

conn = sqlite3.connect('studentdb.db')
c = conn.cursor()

loop = 1
while loop == 1:
        print("Please Select An Option")
        text = input("1. Display All Students, 2. Create Students, 3. Update Students, or 4. Delete Students ")

        # Display all Students and their Attributes
        if text == 1:
            c.execute("SELECT * FROM student")
            all_rows = c.fetchall()

            for r in all_rows:
                print(r)

        # Create Students
        elif text == 2:
            # All Required Info to Create a New Student
            fName = raw_input("First Name: ")
            lName = raw_input("Last Name: ")
            grade = input("GPA: ")
            major_ = raw_input("Major: ")
            fAdvisor = raw_input("Faculty Advisor: ")
            input_param = (fName, lName, grade, major_, fAdvisor,)

            c.execute("INSERT INTO student('FirstName', 'LastName', 'GPA', 'Major', 'FacultyAdvisor')"
                      "VALUES (?, ?, ?, ?, ?)", (fName, lName, grade, major_, fAdvisor))
            print(fName + " " + lName + " Has Been Created")

        # Update Students
        elif text == 3:
            changeOption = input("What Would You Like to Change About Student?"
                                 " 1. Major, 2. Faculty Advisor ")

            sIDUpdate = input("What is the Student's ID Number: ")

            if changeOption == 1:
                majorUpdate = raw_input("New Student Major: ")
                c.execute("UPDATE student SET Major = ? WHERE StudentId = ?", (majorUpdate, sIDUpdate))

            elif changeOption == 2:
                fAdvisorUpdate = raw_input("New Faculty Advisor: ")
                c.execute("UPDATE student SET FacultyAdvisor = ? WHERE StudentId = ?", (fAdvisorUpdate, sIDUpdate))

            print("Student Has Been Updated")

        # Delete Students
        elif text == 4:
            removeID = input("What is the Student ID you would like to remove: ")
            c.execute("DELETE FROM student WHERE StudentId = ?", (removeID,))

            print("Student Has Been Removed")

        conn.commit()

        loopOption = input("(0 = No, 1 = Yes) Would You Like to Exit: ")
        if loopOption == 0:
            loop == 1

        elif loopOption == 1:
            loop == 0
            break

conn.close()

