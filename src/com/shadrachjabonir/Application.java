package com.shadrachjabonir;

import java.util.ArrayList;
import javax.print.attribute.standard.Sides;

public class Application {
    public static ArrayList<Block> blockchain = new ArrayList<Block>();
    public static int difficulty = 4;

    public static void addBlock(Block block){
        block.mineBlock(difficulty);
        blockchain.add(block);
    }
    
    public static boolean isChainValid() {
        Block currentBlock;
        Block previousBlock;

        // Loop through the blockchain to check hashes
        for (int i = 1; i < blockchain.size(); i++) {
            currentBlock = blockchain.get(i);
            previousBlock = blockchain.get(i - 1);

            // Compare current block's hash with calculated hash
            if (!currentBlock.hash.equals(currentBlock.calculateHash())) {
                System.out.println("Current hashes not equal");
                return false;
            }

            // Compare previous block's hash with the current block's previous hash
            if (!previousBlock.hash.equals(currentBlock.previousHash)) {
                System.out.println("Previous hashes not equal");
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args){
        System.out.println("dorrr");
        blockchain.add(new Block("Genesis Block","0"));
        System.out.println("Mining genesis block");
        blockchain.get(0).mineBlock(difficulty);

        addBlock(new Block("Second block",blockchain.get(blockchain.size() - 1).hash));
        addBlock(new Block("Third block",blockchain.get(blockchain.size() - 1).hash));

        System.out.println("\nBlockchain is valid: " + isChainValid());
    }
}
