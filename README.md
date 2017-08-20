Hex library
===========
Package contains classes to convert bytes to hex representation and vice versa.

HexHelper class contains methods to convert byte array to hex string, to convert hex string to byte array and to check, if string is a hex representation of bytes.

An example, how to convert bytes to hex symbols:

```
String str1 = HexHelper.toHex(new byte[]{1, 5, (byte) 140, (byte) 250, -14}); // str1 = "01058CFAF2"
String str2 = HexHelper.toHex(new byte[]{17, 28, (byte) 179, -14}); // str2 = "111CB3F2"
String str3 = HexHelper.toHex(new byte[]{(byte) 255, (byte) 250, (byte) 180, 17, 94}); // str3 = "FFFAB4115E"
```
 
An example, how to convert hex symbols to bytes:

```
byte[] bytes1 = HexHelper.toBytes("AC120F"); // bytes1 = new byte[]{(byte) 172, 18, 15}
byte[] bytes2 = HexHelper.toBytes("AACD2F"); // bytes2 = new byte[]{(byte) 170, (byte) 205, 47}
byte[] bytes3 = HexHelper.toBytes("aacd2f"); // bytes3 = new byte[]{(byte) 170, (byte) 205, 47}
```
 
HexHelper class contains all data in memory.
For large data (for example, big files) this is not efficient.
In such cases HexInputStream and HexOutputStream classes could be used.
HexInputStream read stream of hex symbols and translates them to bytes.
HexOutputStream writes hex symbols to the stream.

An example, how to write hex symbols to the file:

```
try (FileInputStream inputStream = new FileInputStream("some input file");
     HexOutputStream outputStream = new HexOutputStream(new FileOutputStream("some output file"))) {
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

Each byte is represented with two hex symbols.
That is why hex representation doubles the original data size.
