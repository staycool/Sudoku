/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hopa.kid.sudoku;

/**
 *
 * @author KID
 */
class Density implements Comparable<Density> {

    private int mIndexX;
    private int mIndexY;
    private int mdesity;

    public Density(int x, int y, int d) {
        this.mIndexX = x;
        this.mIndexY = y;
        this.mdesity = d;
    }

    /**
     * @return the mIndexX
     */
    public int getmIndexX() {
        return mIndexX;
    }

    /**
     * @param mIndexX the mIndexX to set
     */
    public void setmIndexX(int mIndexX) {
        this.mIndexX = mIndexX;
    }

    /**
     * @return the mIndexY
     */
    public int getmIndexY() {
        return mIndexY;
    }

    /**
     * @param mIndexY the mIndexY to set
     */
    public void setmIndexY(int mIndexY) {
        this.mIndexY = mIndexY;
    }

    /**
     * @return the mdesity
     */
    public int getMdesity() {
        return mdesity;
    }

    /**
     * @param mdesity the mdesity to set
     */
    public void setMdesity(int mdesity) {
        this.mdesity = mdesity;
    }

    //降序排列dsc
    @Override
    public int compareTo(Density o) {
        return o.getMdesity() - this.getMdesity();
    }

    @Override
    public String toString() {
        return "desity-" + this.mdesity + " x-" + this.mIndexX + " y-" + this.mIndexY;
    }
}
