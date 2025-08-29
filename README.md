# Planner App - Task Manager for Android

This Android app is a **task manager** designed to help users stay organized by allowing them to add, view, and manage tasks related to their responsibilities. It was developed as part of a group project for the **Mobile Application Development (COSC439)** course.

## Contributors:
- Mark Alexander
- Matthew Burton
- Adam Parks

## Project Overview

The **Planner App** is built to assist users in organizing their daily responsibilities. With features like task creation, calendar integration, and a task list view, users can stay on top of their deadlines. The app leverages Android’s **Jetpack Compose** and **Room** database for a modern, robust, and scalable architecture.

### Key Features:
- **Task Management**: Add, view, and manage your daily tasks with ease.
- **Calendar View**: A fully integrated calendar allows users to see tasks assigned to specific dates.
- **Task Details**: View detailed information about your tasks and set due dates, reminders, and more.
- **Navigation**: Seamless navigation between the different views (task list, calendar, task details).
  
## My Role

In the development of this app, I took the following responsibilities:
- **Designing and Implementing the Calendar**: I created the calendar UI where users can view tasks scheduled for different dates.
- **Integrating the Database**: I handled the integration of the **Room** database for task persistence and management.
- **Implementing ViewModels and Navigation**: I implemented **ViewModels** to manage UI-related data in a lifecycle-conscious way and set up navigation between screens.

## Technologies Used:
- **Jetpack Compose**: Used for building the user interface in a declarative and reactive style.
- **Room Database**: Provides a local database solution for persisting tasks and their associated data.
- **Compose Material Dialogs**: A third-party library for implementing date pickers and other dialogs in a modern Compose UI style.

## Screenshots

Here are some screenshots showcasing the app’s interface:

### 1. Calendar Page:
The calendar page allows users to view their tasks based on the selected date.

![Calendar Page](https://github.com/marksalamander/Planner_App/assets/143564826/35093bef-dc95-4a50-bd99-ed3ea078e1c6)

### 2. Selected Date Page:
This page displays the tasks scheduled for a specific date, giving the user a detailed view of their day’s responsibilities.

![Selected Date Page](https://github.com/marksalamander/Planner_App/assets/143564826/f5049cbd-e056-4b23-822b-6c76350e6b33)

### 3. Task List Page:
The task list page shows a list of all tasks, which the user can manage and interact with.

![Task List Page](https://github.com/marksalamander/Planner_App/assets/143564826/9eb1c60d-04dd-4ed2-88ff-96c6ec881f48)

### 4. Add Task Page:
Users can add new tasks with details like the task description, due date, and more.

![Add Task Page](https://github.com/marksalamander/Planner_App/assets/143564826/a1343876-5bb9-45d1-975e-1c02b02c4e64)

### 5. Date Picker:
The date picker, implemented using the **Compose Material Dialogs** library, allows users to select dates in a streamlined dialog.

![Date Picker](https://github.com/marksalamander/Planner_App/assets/143564826/6f194c4e-518c-4cf3-be86-6663b22cfd9a)

## Libraries/Resources Used:

- **[Compose Material Dialogs](https://github.com/PranavMaganti/compose-material-dialogs)**: For creating custom dialogs, including the date picker.
- **Room**: For local data persistence, ensuring that tasks are saved and retrieved efficiently.
- **Jetpack Compose**: For building the user interface in a modern, declarative way.
