package com.github.nkoutroumanis;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.esotericsoftware.kryo.util.DefaultInstantiatorStrategy;
import org.junit.Test;
import org.objenesis.strategy.StdInstantiatorStrategy;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Random;

import static org.junit.Assert.*;

public class QuadTreeTest {

    @Test
    public void serializeQuadTree() throws FileNotFoundException {


        long startTime = System.currentTimeMillis();
        QuadTree quadTree = QuadTree.newQuadTree(0,0,1000,1000, 100);

        Random r = new Random();
        for(int i=0;i<1000000;i++)
        {
            //System.out.println(i);
            quadTree.insertPoint(Point.newPoint(Math.random()*0.5d,Math.random()*0.5d));
        }

        System.out.println("Number of contained points of Tree "+ quadTree.getNumberOfInsertedPoints());




//        quadTree.insertPoint(Point.newPoint(1.00000000000000015,1.00000000000000015));
//        quadTree.insertPoint(Point.newPoint(1.0000000000000011,1.0000000000000011));

        System.out.println("Construction Tree time (ms): " + (System.currentTimeMillis()-startTime));

        Kryo kryo = new Kryo();
        kryo.register(QuadTree.class);
        kryo.register(Node.class);
        kryo.register(Point[].class);
        kryo.register(Point.class);
        kryo.setReferences(true);


//        Output output = new Output(new FileOutputStream("./src/test/resources/serializedTree/file.bin"));
//        kryo.writeObject(output, quadTree);
//        output.close();

//        Input input = new Input(new FileInputStream("file.bin"));
//        SomeClass object2 = kryo.readObject(input, SomeClass.class);
//        input.close();


    }


    @Test
    public void deserializeQuadTree() throws FileNotFoundException {

        Kryo kryo = new Kryo();
        kryo.register(QuadTree.class);
        kryo.register(Node.class);
        kryo.register(Point[].class);
        kryo.register(Point.class);
        kryo.setReferences(true);
        kryo.setInstantiatorStrategy(new DefaultInstantiatorStrategy(new StdInstantiatorStrategy()));
        // kryo.setReferences(true);

        Input input = new Input(new FileInputStream("./src/test/resources/serializedTree/file.bin"));

        QuadTree quadTree = kryo.readObject(input, QuadTree.class);
        //input.close();

        System.out.println("Number of contained points of Tree "+ quadTree.getNumberOfInsertedPoints());


    }
}