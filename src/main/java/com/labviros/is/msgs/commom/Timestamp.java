/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.labviros.is.msgs.commom;

import com.labviros.is.Message;
import org.msgpack.core.MessageBufferPacker;
import org.msgpack.core.MessagePack;
import org.msgpack.core.MessageUnpacker;

/**
 *
 * @author clebeson
 */
public class Timestamp extends Message{
    private long nanoseconds=System.nanoTime();

    public Timestamp(Message copy) throws Exception {
        super(copy);
        this.unpack();
    }

    
    
    @Override
    public void pack() throws Exception {
        MessageBufferPacker packer = MessagePack.newDefaultBufferPacker();
        packer.packArrayHeader(1);
        packer.packLong(nanoseconds);
        packer.close();
        setBody(packer.toByteArray());
    }

    @Override
    public void unpack() throws Exception {
        MessageUnpacker unpacker = MessagePack.newDefaultUnpacker(getBody());
        if (unpacker.unpackArrayHeader() != 1) {
            throw new RuntimeException("Bad Length");
        }
        nanoseconds = unpacker.unpackLong();
    }
     
    public long getNanoseconds() {
        return nanoseconds;
    }
    
    
}