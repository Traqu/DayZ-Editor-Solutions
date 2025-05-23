**Version 1.4. - 03.01.24.**
- Added "Delete custom files" button, within new optionalButtonPanel
    - This button upon interaction will automatically remove every custom .xml extension added to your extracting tool.
    - Besides that, it will work just like skipping button, and take you to extraction view.
- Changed prompt text in initial view
***
**Version 1.4.1. - 14.03.24.**
- Code structure update.

**Version 1.5.0. - 14.03.24.**
- Added tool selection window.

**Version 2.0.0. - 15.03.24.**
- Added dynamic event adapter mechanism.

**Version 2.0.1. - 16.03.24**
- Changed tool names.

**Version 2.1.0. - 16.03.24**
- Now Event adapter will respect mapgroupproto entries (also custom), and automatically include `deloot,minloot,maxloot` fields.

**Version 2.2.0. - 18.03.24**
- Fixed issue, where event adapter tool was not including quotation sign, behind class names for items that had `deloot,minloot,maxloot`.

**Version 2.2.1. - 18.03.24**
- Changed text on one of the buttons in ToolPicker to more accurately represent actual usage of that tool. 

**Version 2.3.0. - 20.03.24**
- Now `Event adapter` doesn't export 'y' values.

**Version 2.3.1. - 02.04.24**
- Fixed a typo for objects that do not spawn loot.

**Version 2.4.0. - 02.04.24**
- Added welcome window.

**Version 2.5.0. - 11.04.24**
- Added Takistan & Namalsk mapgroupproto class names to default filtering list.

**Version 3.0.0. - 23.06.24**
- Now if you are using `@More Dynamic Events Objects` mod, you will not have to worry about manually replacing first object in your group to be on top of the file, assuming that you have used the special object `Traqu__[...]` (double underscore! it should be on the top of modded items list).
- Available files are now displayed vertically.

**Version 3.1.0. - 28.06.24**
- Added JHNY complexity fix
- Now protoFiles are properly handled

**Version 3.1.1. - 03.07.24**
- Fixed reading map data from resource files

**Version 3.1.2. - 09.01.25**
- Reworked how properties are read to the title bar
- Added Sakhal proto
- The Welcoming window displays for 2 instead of 3 seconds now

**Version 3.1.3. - 08.05.25**
- Updated Sakhal resource prototypes file
****