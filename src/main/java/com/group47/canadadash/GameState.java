package com.group47.canadadash;

import java.util.ArrayList;
import java.util.ArrayList.*;

/**
 * This class represents the state of the game
 * This state consists of:
 * the amount of remained lives, leaves, stars, boxes, fences, holes, and checkpoints
 * there are helper, getter, and setter methods to access these attributes
 */
public class GameState {

    private int maxLives = 5;
    private boolean lost;
    private ArrayList<Heart> lives;
    private ArrayList<Star> checkpoints;
    private ArrayList<Leaf> leaves;
    private ArrayList<Fence> fences;
    private ArrayList<Box> boxes;
    private ArrayList<Hole> holes;
    private int totalPoints;
    private String province;
    private boolean pause;
    private String[] inventory;

    /**
     * Constructor to initialize the very first state of the Game
     */
    public GameState(){
        // initializing the (x,y) location of hearts
        int space = 15;
        lives = new ArrayList<Heart>(5);
        for (int i = 0; i < maxLives; i++) {
            lives.add(new Heart(30 + i*space, 30));
        }
        // initializing an empty arraylist for checkpoints, leaves, fences, and boxes
        checkpoints = new ArrayList<Star>();
        leaves = new ArrayList<Leaf>();
        fences = new ArrayList<Fence>();
        boxes = new ArrayList<Box>();
        holes = new ArrayList<Hole>();

        totalPoints = 0;
        province = "";
        lost = false;
    }

    /**
     * getter of the attribute totalPoint
     * @return total points which achieved by far
     */
    public int getTotalPoints() {
        return totalPoints;
    }

    /**
     * increasing the attribute totalPoint
     * @param pointsToBeDecreased points got from answering right to the questions
     */
    public void decreasePoints(int pointsToBeDecreased){
        totalPoints = totalPoints - pointsToBeDecreased;
    }

    /**
     * decreasing the attribute totalPoint
     * @param pointsToBeIncreased penalties which got from answering wrong to questions
     */
    public void increasePoints(int pointsToBeIncreased){
        totalPoints = totalPoints + pointsToBeIncreased;
    }

    /**
     * getter of the attribute lives
     * @return the current number of lives
     */
    public int getLives() {
        return lives.size();
    }

    /**
     * decrease a life by one,
     */
    public void loseLife(){
        for (int i = 0; i < 5; i++) {
            if (lives.get(i).isFullHeart()){
                lives.get(i).setHeart(false);
                return;
            }
        }
        lost = true;

    }

    /**
     * getter of attribute stars
     * @return arraylist of stars
     */
    public ArrayList<Star> getCheckpoints() {
        return checkpoints;
    }

    /**
     * add a new star to checkpoints arraylist
     * @param newCheckpoint is a star
     */
    public void addStar(Star newCheckpoint){
        checkpoints.add(newCheckpoint);
    }

    /**
     * removes a checkpoint from checkpoints list
     * @param checkpoint
     */
    public void removeStar(Star checkpoint){
        checkpoints.remove(checkpoint);
    }

    /**
     * getter of attribute leaves
     * @return an arraylist of leaves
     */
    public ArrayList<Leaf> getLeaves() {
        return leaves;
    }

    /**
     * add new leaf to the arraylist leaves
     * @param newLeaf is a type of leaf
     */
    public void addLeaves(Leaf newLeaf) {
        leaves.add(newLeaf);
    }

    /**
     * removes a leaf from leaves arraylist
     * @param givenLeaf is type of leaf
     */
    public void removeLeaf(Leaf givenLeaf){
        leaves.remove(givenLeaf);
    }

    /**
     * getter of attribute boxes
     * @return an arraylist of boxes
     */
    public ArrayList<Box> getBoxes() {
        return boxes;
    }

    /**
     * add a box to the arraylist of boxes
     * @param newBox is a type of box
     */
    public void addBox(Box newBox){
        boxes.add(newBox);
    }

    /**
     * removes a box from the arraylist boxes
     * @param givenBox is a box
     */
    public void removeBox(Box givenBox){
        boxes.remove(givenBox);
    }

    /**
     * getter of fences arraylist
     * @return an arraylist of fences
     */
    public ArrayList<Fence> getFences() {
        return fences;
    }

    /**
     * add a fence to the fences arraylist
     * @param newFence is a fence
     */
    public void addFence(Fence newFence){
        fences.add(newFence);
    }

    /**
     * removes a fence from fences arraylist
     * @param givenFence is a fence
     */
    public void removeFence(Fence givenFence){
        fences.remove(givenFence);
    }

    /**
     * add a new hole to the holes arraylist
     * @param newHole is a hole
     */
    public void addHole(Hole newHole) {
        holes.add(newHole);
    }

    /**
     * removes a hole from the holes arraylist
     * @param givenHole is a hole
     */
    public void removeHole(Hole givenHole) {
        holes.remove(givenHole);
    }

    /**
     * getter of attribute holes arraylist
     * @return an arraylist of holes
     */
    public ArrayList<Hole> getHoles() {
        return holes;
    }

    /**
     * set the name for province attribute
     * @param province is the name of province
     */
    public void setProvince(String province) {
        this.province = province;
    }

    /**
     * getter of the province name
     * @return name of province
     */
    public String getProvince() {
        return province;
    }

    /**
     * getter of inventory attribute
     * @return an array of all achieved inventories
     */
    public String[] showInventory() {
        return inventory;
    }

    /**
     * checking if the user is already achieved that piece or not
     * @param piece is img address
     * @return true, if the specific required piece is among the achieved inventoried
     *         false, if not
     */
    public boolean hasPiece(String piece){
        for (int i = 0; i < inventory.length; i++) {
            if (inventory[i].toLowerCase().equals(piece.toLowerCase())){
                return true;
            }
        }
        return false;
    }

    /**
     * check whether the user is still playing or not
     * @return true, if the game is paused
     *         false, if the player is still playing
     */
    public boolean isPause() {
        return pause;
    }

    /**
     * set the value for the pause attribute
     * @param pause is a boolean
     */
    public void setPause(boolean pause) {
        this.pause = pause;
    }

    /**
     * check whether the player is still eligible to play
     * @return true, if the user lost the game
     */
    public boolean isLost() {
        return lost;
    }

    /**
     *
     * @param lost set if the user can play or not
     */
    public void setLost(boolean lost){
        this.lost = lost;
    }
}
