this allows you to easily fill a spread sheet with scraped data.

just create a csv with each search query seperated by a comma. It fully supports regex. 

I've attached an example of scraping youtube's trending section that gets the title, url and view count. This can be seen in the test.csv file. Below is also a screen shot of the output in a spreadsheet program(libre calc) but it should look the same in excel, though I haven't tried it.

urls are read from a file named url.txt(Ill allow for custom named file at a later date) with each url on a new line. 

Example output for the example input:

![Example output](http://i.imgur.com/0cKAwYg.png)


# issues
* if your search query contains a comma, it will break.

* will only work on a new (empty) spread sheet. I want add support to edit existing spreadsheets

* no way to have custom titles for each row yet. Again, i'll probably add this sooner or later

# other stuff
I made this to assist me in some work I was doing a little while ago. I figured someone else may find it useful for something.

Also, i'm still fairly new to programming so if you see any issues/bugs - really anything that is bad - please let me know. I
would greatly appreciate it! 
