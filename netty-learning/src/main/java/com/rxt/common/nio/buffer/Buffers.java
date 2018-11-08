/**
 * FileName: Buffers
 * Author:   Ren Xiaotian
 * Date:     2018/11/8 20:23
 */

package com.rxt.common.nio.buffer;

import java.nio.ByteBuffer;

/**
 * 自定义buffer
 */
public class Buffers {
    ByteBuffer readBuffer;
    ByteBuffer writeBuffer;

    public Buffers(int readCapacity, int writeCapacity) {
        readBuffer = ByteBuffer.allocate(readCapacity);
        writeBuffer = ByteBuffer.allocate(writeCapacity);
    }

    public ByteBuffer getReadBuffer() {
        return readBuffer;
    }

    public ByteBuffer getWriteBuffer() {
        return writeBuffer;
    }
}
