Hex library
===========
Hex library converts bytes to the hex representation and vice versa.

`HexHelper` class contains methods to convert the byte array to the hex string, to convert the hex string to the byte array and to check, whether the string is a hex representation of bytes or not.

An example, how to convert the byte array to the hex string:
```
String str1 = HexHelper.toHex(new byte[]{1, 5, (byte) 140, (byte) 250, -14}); // str1 = "01058cfaf2"
String str2 = HexHelper.toHex(new byte[]{17, 28, (byte) 179, -14}); // str2 = "111cb3f2"
String str3 = HexHelper.toHex(new byte[]{(byte) 255, (byte) 250, (byte) 180, 17, 94}); // str3 = "fffab4115e"
```

An example, how to convert the hex string to the byte array:
```
byte[] bytes1 = HexHelper.toBytes("AC120F"); // bytes1 = new byte[]{(byte) 172, 18, 15}
byte[] bytes2 = HexHelper.toBytes("AACD2F"); // bytes2 = new byte[]{(byte) 170, (byte) 205, 47}
byte[] bytes3 = HexHelper.toBytes("aacd2f"); // bytes3 = new byte[]{(byte) 170, (byte) 205, 47}
```

`HexHelper` class contains all data in memory.
For the large data (for example, big files) this is not efficient.
In this case `HexInputStream` and `HexOutputStream` classes can be used.
`HexInputStream` reads the stream of the hex characters and translates them to the bytes.
`HexOutputStream` translates the bytes to the hex characters and writes them to the stream.

An example, how to write the hex characters to the file:
```
try (FileInputStream inputStream = new FileInputStream("input file");
     HexOutputStream outputStream = new HexOutputStream(new FileOutputStream("hex output file"))) {
    int b;
    while (true) {
        b = inputStream.read();
        if (b < 0) {
            break;
        }
        outputStream.write(b);
    }
}
```

Each byte is represented with two hex characters.
The hex representation of bytes increases the original size twice.

Donation
========
If you find my code useful, you can [bye me a coffee](https://www.paypal.me/dshapovalov)
