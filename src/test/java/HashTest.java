import static org.junit.Assert.*;

public class HashTest {

    @org.junit.Test
    public void TestHash() {
        String expected = "4925f41c60a58cd6f62f604960e4fddee7324c96a7c4ac4504a4855e1ad8724b";
        String teststring = "kristofer";

        String actual = Hash.hexString(Hash.hash(teststring.getBytes()));
        System.out.printf("%s : %s\n", teststring, actual );

        assertTrue(actual.equals(expected));
    }
    @org.junit.Test
    public void TestHashVince() {
        String expected = "f44c4b0766fc9f085ee869651e9e435f80588520e3db0f08a2112891d29dc5ab";

        String teststring = "Vincent";

        String actual = Hash.hexString(Hash.hash(teststring.getBytes()));
        System.out.printf("%s : %s\n", teststring, actual );

        assertTrue(actual.equals(expected));
    }
    @org.junit.Test
    public void TestHash2() {
        String expected = "d04c6cdc05d09fbb802679117ac3b4c9114e9f989c69879a8ffbe216ac91d843";

        String teststring = "com.zipcode.wilmington";

        String actual = Hash.hexString(Hash.hash(teststring.getBytes()));
        System.out.printf("%s : %s\n", teststring, actual );

        assertTrue(actual.equals(expected));
    }
}