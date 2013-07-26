package com.jordan.tunnelgame;

public class LevelStorage {

    /*pack ids:
    0: test

    name#background#level

    TILE CHAR IDS
    '  ' : empty space
    'f!' : finish tile

    'b+' : basic tile
    'b-' : rock tile
    'b/' : stone tile
    'b^' : snow tile
    'b&' : metal tile

    'd*' : fire tile

    's_' : ice tile

    'w0'123456789:;<=>?@ABCDEFGHIJKLMNOPQRSTUVWXYZ : warp tile
            (their id is as they appear, they target the # representing them )

    CHASER/ORB IDS 'a-j' 'A-J'
    'Ca' - 'Cj' : default chaser
    'Oa' - 'Oj; : default orb

    TO REMEMBER WHEN ADDING:
    1) add picture in assets folder
    2) load picture in assets class
    3) define char here
    4) make it recognised by the level class switch statement
    5) add a class that extends tile, implements methods (jordan only)
    6) getImage needs to return the correct image
    7) add functionality (in the class, in GameRunner) (jordan only)
    8) add it to a level

    HOW TO ADD A NEW BACKGROUND
    1) put it in assets folder, name it backgroundwhatever.png
    2) make it an instance variable here
    3) load it in the load method
    4) add an if statement in the backgroundByString method
    5) add it to a level after the first hashtaga
    */

    public static String getLevelString(int packID, int levelNum)
    {
        switch (packID * 100 + levelNum)
        {
            case -1: return ScreenLevelEditor.levelName + "#" + ScreenLevelEditor.backgroundString + "#" + ScreenLevelEditor.levelString;
            case 1:
                return "test1#moon#" +
                        "  w0                            " +
                        "                w2      Ob      " +
                        "            Oa    f!            " +
                        "                  b+      Cb    " +
                        "            b+b+b+b+d*b+b+b+    " +
                        "b+  w1                          " +
                        "  b+                            " +
                        "b+b+b+          Ca              " +
                        "b+b+b+b+b+            w0  b+b+d*" +
                        "b+b+b+b+b+b+b+b+b+b+b+b+b+b+b+b+";
            case 2:
                return "test2#sunset#" +
                        "  w0                            " +
                        "                w2      Ob      " +
                        "            Oa    f!            " +
                        "                  b+      Cb    " +
                        "            b+b+b+b+d*b+b+b+    " +
                        "b+  w1                          " +
                        "  b+                            " +
                        "  b+b+          Ca              " +
                        "b+b+b+b+b+            w0  b+b+d*" +
                        "b+b+b+b+b+b+b+b+b+b+b+b+b+b+b+b+";
            case 3:
                return "test3#night#" +
                        "  w0                            " +
                        "                w2      Ob      " +
                        "            Oa    f!            " +
                        "                  b+      Cb    " +
                        "            b+b+b+b+d*b+b+b+    " +
                        "b+  w1                          " +
                        "  b+                            " +
                        "  b+b+          Ca              " +
                        "  b+b+b+b+            w0  b+b+d*" +
                        "b+b+b+b+b+b+b+b+b+b+b+b+b+b+b+b+";
            case 4:
                return "test4##" +
                        "  w0                            " +
                        "                w2      Ob      " +
                        "            Oa    f!            " +
                        "                  b+      Cb    " +
                        "            b+b+b+b+d*b+b+b+    " +
                        "b+  w1                          " +
                        "  b+                            " +
                        "  b+b+          Ca              " +
                        "  b+b+b+b+            w0  b+b+d*" +
                        "  b+b+b+b+b+b+b+b+b+b+b+b+b+b+b+";
            case 5:
                return "le0#sunset#" +
                        "b+                              " +
                        "  b+b+                        b+" +
                        "    b+      Oa              b+  " +
                        "b+    b+  b+            b+b+    " +
                        "      b+b+            b+b+b+    " +
                        "          b+b+        b+        " +
                        "    b+  b+  b+      b+          " +
                        "        b+    b+  b+            " +
                        "  f!                        Ca  " +
                        "b+b+b+b+b+b+b+b+b+b+b+b+b+b+b+b+";
            case 6:
                return "le0#sunset#" +
                        "b+                              " +
                        "  b+b+                        b+" +
                        "    b+      Oa              b+  " +
                        "b+    b+  b+            b+b+    " +
                        "      b+b+            b+b+b+    " +
                        "          b+b+        b+        " +
                        "    b+  b+  b+      b+          " +
                        "        b+    b+  b+            " +
                        "  f!                        Ca  " +
                        "b+b+b+b+b+b+b+b+b+b+b+b+b+b+b+b+";
            case 7:
                return "le0#sunset#" +
                        "b+                              " +
                        "  b+b+                        b+" +
                        "    b+      Oa              b+  " +
                        "b+    b+  b+            b+b+    " +
                        "      b+b+            b+b+b+    " +
                        "          b+b+        b+        " +
                        "    b+  b+  b+      b+          " +
                        "        b+    b+  b+            " +
                        "  f!                        Ca  " +
                        "b+b+b+b+b+b+b+b+b+b+b+b+b+b+b+b+";
            case 8:
                return "le0#sunset#" +
                        "b+                              " +
                        "  b+b+                        b+" +
                        "    b+      Oa              b+  " +
                        "b+    b+  b+            b+b+    " +
                        "      b+b+            b+b+b+    " +
                        "          b+b+        b+        " +
                        "    b+  b+  b+      b+          " +
                        "        b+    b+  b+            " +
                        "  f!                        Ca  " +
                        "b+b+b+b+b+b+b+b+b+b+b+b+b+b+b+b+";
            case 9:
                return "le0#sunset#" +
                        "b+                              " +
                        "  b+b+                        b+" +
                        "    b+      Oa              b+  " +
                        "b+    b+  b+            b+b+    " +
                        "      b+b+            b+b+b+    " +
                        "          b+b+        b+        " +
                        "    b+  b+  b+      b+          " +
                        "        b+    b+  b+            " +
                        "  f!                        Ca  " +
                        "b+b+b+b+b+b+b+b+b+b+b+b+b+b+b+b+";
            case 10:
                return "le0#sunset#" +
                        "b+                              " +
                        "  b+b+                        b+" +
                        "    b+      Oa              b+  " +
                        "b+    b+  b+            b+b+    " +
                        "      b+b+            b+b+b+    " +
                        "          b+b+        b+        " +
                        "    b+  b+  b+      b+          " +
                        "        b+    b+  b+            " +
                        "  f!                        Ca  " +
                        "b+b+b+b+b+b+b+b+b+b+b+b+b+b+b+b+";
            case 11:
                return "le0#sunset#" +
                        "b+                              " +
                        "  b+b+                        b+" +
                        "    b+      Oa              b+  " +
                        "b+    b+  b+            b+b+    " +
                        "      b+b+            b+b+b+    " +
                        "          b+b+        b+        " +
                        "    b+  b+  b+      b+          " +
                        "        b+    b+  b+            " +
                        "  f!                        Ca  " +
                        "b+b+b+b+b+b+b+b+b+b+b+b+b+b+b+b+";
            case 12:
                return "le0#sunset#" +
                        "b+                              " +
                        "  b+b+                        b+" +
                        "    b+      Oa              b+  " +
                        "b+    b+  b+            b+b+    " +
                        "      b+b+            b+b+b+    " +
                        "          b+b+        b+        " +
                        "    b+  b+  b+      b+          " +
                        "        b+    b+  b+            " +
                        "  f!                        Ca  " +
                        "b+b+b+b+b+b+b+b+b+b+b+b+b+b+b+b+";
            case 13:
                return "le0#sunset#" +
                        "b+                              " +
                        "  b+b+                        b+" +
                        "    b+      Oa              b+  " +
                        "b+    b+  b+            b+b+    " +
                        "      b+b+            b+b+b+    " +
                        "          b+b+        b+        " +
                        "    b+  b+  b+      b+          " +
                        "        b+    b+  b+            " +
                        "  f!                        Ca  " +
                        "b+b+b+b+b+b+b+b+b+b+b+b+b+b+b+b+";
            case 14:
                return "le0#sunset#" +
                        "b+                              " +
                        "  b+b+                        b+" +
                        "    b+      Oa              b+  " +
                        "b+    b+  b+            b+b+    " +
                        "      b+b+            b+b+b+    " +
                        "          b+b+        b+        " +
                        "    b+  b+  b+      b+          " +
                        "        b+    b+  b+            " +
                        "  f!                        Ca  " +
                        "b+b+b+b+b+b+b+b+b+b+b+b+b+b+b+b+";
            case 15:
                return "le0#sunset#" +
                        "b+                              " +
                        "  b+b+                        b+" +
                        "    b+      Oa              b+  " +
                        "b+    b+  b+            b+b+    " +
                        "      b+b+            b+b+b+    " +
                        "          b+b+        b+        " +
                        "    b+  b+  b+      b+          " +
                        "        b+    b+  b+            " +
                        "  f!                        Ca  " +
                        "b+b+b+b+b+b+b+b+b+b+b+b+b+b+b+b+";
            case 16:
                return "le0#sunset#" +
                        "b+                              " +
                        "  b+b+                        b+" +
                        "    b+      Oa              b+  " +
                        "b+    b+  b+            b+b+    " +
                        "      b+b+            b+b+b+    " +
                        "          b+b+        b+        " +
                        "    b+  b+  b+      b+          " +
                        "        b+    b+  b+            " +
                        "  f!                        Ca  " +
                        "b+b+b+b+b+b+b+b+b+b+b+b+b+b+b+b+";
            case 17:
                return "le0#sunset#" +
                        "b+                              " +
                        "  b+b+                        b+" +
                        "    b+      Oa              b+  " +
                        "b+    b+  b+            b+b+    " +
                        "      b+b+            b+b+b+    " +
                        "          b+b+        b+        " +
                        "    b+  b+  b+      b+          " +
                        "        b+    b+  b+            " +
                        "  f!                        Ca  " +
                        "b+b+b+b+b+b+b+b+b+b+b+b+b+b+b+b+";
            case 18:
                return "le0#sunset#" +
                        "b+                              " +
                        "  b+b+                        b+" +
                        "    b+      Oa              b+  " +
                        "b+    b+  b+            b+b+    " +
                        "      b+b+            b+b+b+    " +
                        "          b+b+        b+        " +
                        "    b+  b+  b+      b+          " +
                        "        b+    b+  b+            " +
                        "  f!                        Ca  " +
                        "b+b+b+b+b+b+b+b+b+b+b+b+b+b+b+b+";
            case 19:
                return "le0#sunset#" +
                        "b+                              " +
                        "  b+b+                        b+" +
                        "    b+      Oa              b+  " +
                        "b+    b+  b+            b+b+    " +
                        "      b+b+            b+b+b+    " +
                        "          b+b+        b+        " +
                        "    b+  b+  b+      b+          " +
                        "        b+    b+  b+            " +
                        "  f!                        Ca  " +
                        "b+b+b+b+b+b+b+b+b+b+b+b+b+b+b+b+";
            case 20:
                return "le0#sunset#" +
                        "b+                              " +
                        "  b+b+                        b+" +
                        "    b+      Oa              b+  " +
                        "b+    b+  b+            b+b+    " +
                        "      b+b+            b+b+b+    " +
                        "          b+b+        b+        " +
                        "    b+  b+  b+      b+          " +
                        "        b+    b+  b+            " +
                        "  f!                        Ca  " +
                        "b+b+b+b+b+b+b+b+b+b+b+b+b+b+b+b+";
            case 21:
                return "le0#sunset#" +
                        "b+                              " +
                        "  b+b+                        b+" +
                        "    b+      Oa              b+  " +
                        "b+    b+  b+            b+b+    " +
                        "      b+b+            b+b+b+    " +
                        "          b+b+        b+        " +
                        "    b+  b+  b+      b+          " +
                        "        b+    b+  b+            " +
                        "  f!                        Ca  " +
                        "b+b+b+b+b+b+b+b+b+b+b+b+b+b+b+b+";
            case 22:
                return "le0#sunset#" +
                        "b+                              " +
                        "  b+b+                        b+" +
                        "    b+      Oa              b+  " +
                        "b+    b+  b+            b+b+    " +
                        "      b+b+            b+b+b+    " +
                        "          b+b+        b+        " +
                        "    b+  b+  b+      b+          " +
                        "        b+    b+  b+            " +
                        "  f!                        Ca  " +
                        "b+b+b+b+b+b+b+b+b+b+b+b+b+b+b+b+";
            case 23:
                return "le0#sunset#" +
                        "b+                              " +
                        "  b+b+                        b+" +
                        "    b+      Oa              b+  " +
                        "b+    b+  b+            b+b+    " +
                        "      b+b+            b+b+b+    " +
                        "          b+b+        b+        " +
                        "    b+  b+  b+      b+          " +
                        "        b+    b+  b+            " +
                        "  f!                        Ca  " +
                        "b+b+b+b+b+b+b+b+b+b+b+b+b+b+b+b+";
            case 24:
                return "le0#sunset#" +
                        "b+                              " +
                        "  b+b+                        b+" +
                        "    b+      Oa              b+  " +
                        "b+    b+  b+            b+b+    " +
                        "      b+b+            b+b+b+    " +
                        "          b+b+        b+        " +
                        "    b+  b+  b+      b+          " +
                        "        b+    b-  b+            " +
                        "  f!                        Ca  " +
                        "b+b+b+b+b+b+b+b+b+b+b+b+b+b+b+b+";
            case 25:
                return "le0#sunset#" +
                        "b+                              " +
                        "  b+b+                        b+" +
                        "    b+      Oa              b+  " +
                        "b+    b+  b-            b-b+    " +
                        "      b-b-            b-b+b+    " +
                        "          b-b-        b-        " +
                        "                                " +
                        "                                " +
                        "  f!                        Ca  " +
                        "b+b+b+b+b+b+b+b+b+b+b+b+b+b+b+b+";

        }

        return "";

    }

}