package com.unbrick.xbox.XBDM;

import java.io.UnsupportedEncodingException;

public class HexUtils {
    public static int hex2decimal(String s) {
        String digits = "0123456789ABCDEF";
        s = s.toUpperCase();
        int val = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            int d = digits.indexOf(c);
            val = 16 * val + d;
        }
        return val;
    }

    public static String formatHexDump(byte[] array, int offset, int length) {
        final int width = 16;

        StringBuilder builder = new StringBuilder("");

        for (int rowOffset = offset; rowOffset < offset + length; rowOffset += width) {
            if (rowOffset == 0) {
                builder.append(String.format("%06d:  ", rowOffset));
            } else {
                builder.append(String.format("          %06d:  ", rowOffset));
            }

            for (int index = 0; index < width; index++) {
                if (rowOffset + index < array.length) {
                    builder.append(String.format("%02x ", array[rowOffset + index]));
                } else {
                    builder.append("   ");
                }
                if (index % 7 == 0 && index % 14 != 0) {
                    builder.append("   ");
                }
            }

            if (rowOffset < array.length) {
                int asciiWidth = Math.min(width, array.length - rowOffset);
                builder.append("  |  ");
                try {
                    builder.append(new String(array, rowOffset, asciiWidth, "UTF-8").replaceAll("\r\n", " ").replaceAll("\n", " "));
                } catch (UnsupportedEncodingException ignored) {
                    //If UTF-8 isn't available as an encoding then what can we do?!
                }
            }

            builder.append(String.format("%n"));
        }



        return "\r\n" + builder.toString();
    }
}
