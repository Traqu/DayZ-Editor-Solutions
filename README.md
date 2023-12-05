# DayZ Editor - Purifier <br> &nbsp; &nbsp;  created by Traqu
___
The purpose of this application is to automate the 'cleansing' of exported MapGroupPos.xml files from records that do not have assigned spawn points.<br>
<br>
**1. &nbsp; Home screen**

![home](https://github.com/TraquPjatk/DayZ-Editor-Purifier/assets/101177758/1981735d-62a5-4bc0-976f-8a347c5802e4)

**1.1. &nbsp; You will be greeted by a view that allows you to import customised proto-type files.**
- **Add** - this button will redirect you to a file chooser(**step 2.**), that will allow you to select a file, that you want to extract 'spawning-things' `class names` from.
- **Skip** - this button will redirect you to another file chooser (**step 3.**), that will allow you to choose a file, that you wish to fetch records from.
<br>

**2. &nbsp; Custom file importer**

![addCustom](https://github.com/TraquPjatk/DayZ-Editor-Purifier/assets/101177758/f8f3c13f-65a2-4165-a932-8aafe0a99145)
 - Here you will be taken to you **Desktop**.
 - Simply choose a file, that you want to do import from.
 <br>
 - You can use the MapGroupProto file if your custom map has one
 &nbsp;- **example**:`mpmissions/takistan/MapGroupProto.xml`
 - If such a file does not exist, simply select a file that will contain the desired class names.
 &nbsp;- this can be self-prepared
 &nbsp;- the only condition is that each line has to contain: `<group name="` string at the beginning
<br>
- After you have chosen the file, you will be redirected to **step 3.**
<br>
**NOTICE!**
- Such custom file will stay in newly created directory: `` untill manualy removed.
- Files that you have imported are adding up in the process.
- They are taken into consideration each time you perform a cleanup. 
<br>


**3. &nbsp; File select & export**

![export](https://github.com/TraquPjatk/DayZ-Editor-Purifier/assets/101177758/496aa7a2-0283-4b78-b915-0bc2b110dd6c)
This step is pretty straight forward.<br>You simply select and export content from desired file by clicling `export` button.
After doing so, the application will terminate, and newly generated `.txt` file will be opened.
<br>
**4. &nbsp; Result**

![result](https://github.com/TraquPjatk/DayZ-Editor-Purifier/assets/101177758/2a4a11fe-fc00-4072-8b9f-d1c94fc037e5)

This can be copied over to `mapgrouppos.txt` in your server directory.

---