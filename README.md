# Gantt-Manager
A simple desktop application for managing Gantt diagrams with a plain java backend and a swing frontend.

Developed for the Software Development I course at the University of Ioannina, department of Computer Science and Engineering.  

Supervised by professor Panos Vassiliadis.

As described in the IMainController interface, the system:

* Loads a delimited file in the prescribed format and represents its contents (i.e., a Gantt project's tasks) as objects in main memory

* Assuming a Gantt project has been loaded, it returns all the tasks whose TaskTest is prefixed by the method's argument

* Assuming a Gantt project has been loaded, it returns the task whose taskId is equal to the method's argument

* Assuming a Gantt project has been loaded, it returns its top-level tasks 

* Assuming a Gantt project has been loaded, it creates a report in a specified format. The report lists, in a sorted fashion, all the tasks of the project.
