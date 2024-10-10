package com.shadrachjabonir;

import java.util.Date;
import java.security.MessageDigest;

class Block {
    public String hash;
    public String previousHash;
    private String data;
    private long timeStamp;
    private int nonce;

    public Block(String data, String previousHash) {
        this.data = data;
        this.hash = calculateHash();
        this.previousHash = previousHash;
        this.timeStamp = new Date().getTime();
    }

    public String calculateHash() {
        return hashSha256(previousHash + Long.toString(timeStamp) + Integer.toString(nonce) +data);
    }

    private static String hashSha256(String input){
        try{
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = digest.digest(input.getBytes("UTF-8"));
            StringBuilder hexString = new StringBuilder();
            for(byte b : hashBytes){
                String hex = Integer.toHexString(0xff & b);
                if(hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();
        }catch (Exception ex){
            throw new RuntimeException(ex);
        }
    }

    public void mineBlock(int difficulty) {
        String target = new String(new char[difficulty]).replace('\0', '0');
        while (!hash.substring(0,difficulty).equals(target)) {
            nonce++;
            hash = calculateHash();
        }
        System.out.println("Block has beend mined --> " +hash);
    }
}
