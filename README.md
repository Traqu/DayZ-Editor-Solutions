# DayZ Editor - Purifier <br> &nbsp; &nbsp;  created by Traqu
___
The purpose of this application is to automate the 'cleansing' of exported MapGroupPos.xml files from records that do not have assigned spawn points.



## Before you start<br>

<br>**0. &nbsp; Download**
- First step is downloading the app from this GitHub repository.
- To do that enter this link https://github.com/TraquPjatk/DayZ-Editor-Purifier/tree/main/Executable.

![download](https://github.com/TraquPjatk/DayZ-Editor-Purifier/assets/101177758/4ad2bff2-95c9-4625-bd45-2cf55ad11c46)
- You will be redirected to a directory containing the latest release of the application. 

![download_2](https://github.com/TraquPjatk/DayZ-Editor-Purifier/assets/101177758/76f98d04-83a6-4e6c-bcd7-b235c41d2288)
- Here you can either click on `View raw text` or `Download symbol` on the right.
- After you have downloaded application I advise to move it over to your Desktop and run it.

<br>**1. &nbsp; Windows defender**
- The application will always be flagged as malicious the first time it is launched, as it does not have a certificate.<br>

![defender](https://github.com/TraquPjatk/DayZ-Editor-Purifier/assets/101177758/437f3839-711b-4442-8a65-507b997ce21c)
- The solution is to simply click on `More information` underlined text and allow explicitly.

![defender_2](https://github.com/TraquPjatk/DayZ-Editor-Purifier/assets/101177758/81ffe342-ceb4-4b6c-bc04-20cfc690a588)
- 
<br>**2. &nbsp; Java runtime environment**
- You may see Java pop-up window titled **Error Occurred**<br>
- What it says, is that you basically do not have necessary stuff installed to run this app.
 
![JRE](https://github.com/TraquPjatk/DayZ-Editor-Purifier/assets/101177758/c9fc9183-dce2-4c14-a9fa-d178324f793e)
- To solve this problem visit this link: https://www.oracle.com/pl/java/technologies/downloads/#jdk21-windows.

![jdk_download](https://github.com/TraquPjatk/DayZ-Editor-Purifier/assets/101177758/2a2ed17e-d4de-4722-b067-115227f01e71)
- Download suitable file.
  - `JDK21 > Windows > x64 Installer`
    - **Any newer version of JRE than 16.0.1. (included) will work just fine.**
- Run the installation.
<br>
- After the installation is done you can run the programme again.

## Application manual
 <br>**1. &nbsp; Home screen**

![home](https://github.com/TraquPjatk/DayZ-Editor-Purifier/assets/101177758/1981735d-62a5-4bc0-976f-8a347c5802e4)

**1.1. &nbsp; You will be greeted by a view that allows you to import customised proto-type files.**
- **Add** - this button will redirect you to a file chooser (**step 2.**), that will allow you to select a file, that you want to extract 'spawning-things' `class names` from.
- **Skip** - this button will redirect you to another file chooser (**step 3.**), that will allow you to choose a file, that you wish to fetch records from.
  <br>

<br>**2. &nbsp; Custom file importer**

![addCustom](https://github.com/TraquPjatk/DayZ-Editor-Purifier/assets/101177758/f8f3c13f-65a2-4165-a932-8aafe0a99145)
- Here you will be taken to your **Desktop**.
- Simply choose a file, that you want to do import from.
  <br>
- You can use the MapGroupProto file if your custom map has one<br>
&nbsp;- **example**: `mpmissions/takistan/MapGroupProto.xml`
- If such a file does not exist, simply select a file that will contain the desired class names.
  - this can be self-prepared
  - the only condition is that each line has to contain: `<group name="` string at the beginning
    <br>
- After you have chosen the file, you will be redirected to **step 3.**
  
  <br>**NOTICE!**
- Such custom file will stay in newly created directory:<br> `C:\Users\[UserName]\AppData\Local\DayZ EditorPurifier\custom\[CustomFileName]` until manually removed.
- Files that you have imported are adding up in the process.
- They are taken into consideration each time you perform a cleanup.<br>
&nbsp;
- If you have any custom files created, **home** screen will be displaying **browse custom files** button.
  <br>

<br>**3. &nbsp; File select & export**

![export](https://github.com/TraquPjatk/DayZ-Editor-Purifier/assets/101177758/496aa7a2-0283-4b78-b915-0bc2b110dd6c)<br>
This step is pretty straight forward.<br>
- Here you will be taken straight to `DayZ Editor` directory.
- Simply select and export content from desired file by clicking `export` button.
After doing so, the application will terminate, and the newly generated `.txt` file will be opened.
<br>

<br>**4. &nbsp; Result**

![result](https://github.com/TraquPjatk/DayZ-Editor-Purifier/assets/101177758/2a4a11fe-fc00-4072-8b9f-d1c94fc037e5)

This can be copied over to `mapgrouppos.txt` in your server directory.

---