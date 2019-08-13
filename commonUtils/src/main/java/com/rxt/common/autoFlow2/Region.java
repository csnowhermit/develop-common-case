package com.rxt.common.autoFlow;

/**
 * 分区域
 */
public class Region {
    private String flags;
    private Point leftup;
    private Point leftdown;
    private Point rightup;
    private Point rightdown;

    public Region() {
    }

    /**
     * 全部传参数构造
     *
     * @param flags
     * @param leftup
     * @param leftdown
     * @param rightup
     * @param rightdown
     */
    public Region(String flags, Point leftup, Point leftdown, Point rightup, Point rightdown) {
        this.flags = flags;
        this.leftup = leftup;
        this.leftdown = leftdown;
        this.rightup = rightup;
        this.rightdown = rightdown;
    }

    /**
     * 只传左上和右下构造
     *
     * @param flags
     * @param leftup
     * @param rightdown
     */
    public Region(String flags, Point leftup, Point rightdown) {
        this.flags = flags;
        this.leftup = leftup;
        this.rightdown = rightdown;
    }

    public String getFlags() {
        return flags;
    }

    public Region setFlags(String flags) {
        this.flags = flags;
        return this;
    }

    public Point getLeftup() {
        return leftup;
    }

    public Region setLeftup(Point leftup) {
        this.leftup = leftup;
        return this;
    }

    public Point getLeftdown() {
        return leftdown;
    }

    public Region setLeftdown(Point leftdown) {
        this.leftdown = leftdown;
        return this;
    }

    public Point getRightup() {
        return rightup;
    }

    public Region setRightup(Point rightup) {
        this.rightup = rightup;
        return this;
    }

    public Point getRightdown() {
        return rightdown;
    }

    public Region setRightdown(Point rightdown) {
        this.rightdown = rightdown;
        return this;
    }

    @Override
    public String toString() {
        return "Region{" +
                "flags='" + flags + '\'' +
                ", leftup=" + leftup +
                ", leftdown=" + leftdown +
                ", rightup=" + rightup +
                ", rightdown=" + rightdown +
                '}';
    }
}
