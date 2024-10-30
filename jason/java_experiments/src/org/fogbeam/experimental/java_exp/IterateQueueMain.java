package org.fogbeam.experimental.java_exp;

import java.util.Iterator;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class IterateQueueMain
{
	public static void main( String[] args )
	{
		Queue<Integer> queue = new ConcurrentLinkedQueue<Integer>();
		
		queue.add( 1 );
		queue.add( 13 );
		queue.add( 7 );
		queue.add( 24 );
		queue.add( 0 );
		
		// Iterator<Integer> queueIter = queue.iterator();
		
		for( Integer anInt : queue )
		{
			System.out.println( "anInt: " + anInt );
		}
		
		System.out.println( "\n\n" );
		
		Iterator<Integer> queueIter = queue.iterator();
		while( queueIter.hasNext())
		{
			Integer anInt2 = queueIter.next();
			System.out.println( "anInt2: " + anInt2 );
		}
		
		System.out.println( "\n\n" );
		
		while( queue.isEmpty() == false )
		{
			Integer anInt3 = queue.poll();
			System.out.println( "anInt3: " + anInt3 );
		}
		
		System.out.println( "Done" );
	}
}
