import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Logger;

public class Block {
    private final static Logger LOGGER = Logger.getLogger(Block.class.getName());
    private static final AtomicInteger count = new AtomicInteger(0);
    private final static byte[] nullHash = new byte[]{0x0};
    private final static Long maxnonce = 5000000L;
    private final static Long SomeNumber = 567890L;

    // block instance variables
    private byte[] previousHash;
    private String[] transactions;
    private byte[] blockHash;
    private long nonce = 0; //seed for mining work
    private long ordinal = 0; //block number

    public Block(byte[] previousHash, String[] transactions) {
        this.ordinal = count.getAndIncrement();
        this.previousHash = previousHash;
        this.transactions = transactions;
        this.nonce = (long) (Math.random() * SomeNumber);

        this.blockHash = nullHash;
    }

    public Block() { //for Test only
        this.ordinal = count.getAndIncrement();
        this.previousHash = nullHash;
        this.transactions = new String[]{};
        this.nonce = 0;

        this.blockHash = nullHash;
    }

    public long getOrdinal() {
        return this.ordinal;
    }
    public byte[] getPreviousHash() {
        return previousHash;
    }

    public String[] getTransactions() {
        return transactions;
    }

    public byte[] getBlockHash() {
        return blockHash;
    }
    public String getBlockHashString() {
        return Hash.hexString(blockHash);
    }

    public boolean verifyBlock() {
        byte[] transactionshash = Hash.hash(Arrays.toString(this.transactions).getBytes());
        byte[] noncebytes = longToBytes(nonce);
        byte[] hashTry = Hash.hash(noncebytes, transactionshash, this.previousHash);
        if (hashTry.length != blockHash.length) return false;
        for (int i = 0; i < hashTry.length; i++)
            if (hashTry[i] != blockHash[i])
                return false;
        return true;
    }

    public Block mine() {
        Long start = System.currentTimeMillis();
        byte[] transactionshash = Hash.hash(Arrays.toString(this.transactions).getBytes());
        long top = nonce + maxnonce;
        while (nonce < top) {
            byte[] noncebytes = longToBytes(nonce);
            byte[] hashTry = Hash.hash(noncebytes, transactionshash, this.getPreviousHash());
            if (hashTry[0] == 0 && hashTry[1] == 0) { //&& hashTry[2] == 0) {
                System.out.println("mine: nonce: " + nonce);
                System.out.println(Hash.hexString(hashTry));
                this.blockHash = hashTry;
                break;
            }
            if (nonce == top - 1) {
                System.out.println("MaxNonced Out");
            }
            nonce++;
        }
        Long end = System.currentTimeMillis();
        System.out.printf("Mining time for Block %d : %8d ms.\n--\n", this.getOrdinal(), (end - start));
        return this;
    }

    private static byte[] longToBytes(long l) {
        byte[] result = new byte[8];
        for (int i = 7; i >= 0; i--) {
            result[i] = (byte) (l & 0xFF);
            l >>= 8;
        }
        return result;
    }

    public String blockDataString() {
        return String.format("Block[%d] (Verified? %b)\n PrevHash: %s\n Nonce: %s\n Transactions: %s\nBlockHash: %s\n--\n",
                ordinal,
                this.verifyBlock(),
                Hash.hexString(previousHash),
                nonce,
                String.join(" / ", transactions),
                Hash.hexString(blockHash));
    }

}