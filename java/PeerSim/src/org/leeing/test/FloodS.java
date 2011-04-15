/*
 * Copyright (c) 2003 The BISON Project
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License version 2 as
 * published by the Free Software Foundation.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 675 Mass Ave, Cambridge, MA 02139, USA.
 *
 */

package org.leeing.test;
import peersim.core.*;

import peersim.vector.SingleValueHolder;
import peersim.config.*;
import peersim.core.*;
import peersim.transport.Transport;
import peersim.cdsim.CDProtocol;
import peersim.edsim.EDProtocol;

/**
* Event driven version of epidemic averaging.
*/
public class FloodS extends SingleValueHolder
implements CDProtocol, EDProtocol {

//--------------------------------------------------------------------------
// Initialization
//--------------------------------------------------------------------------

/**
 * @param prefix string prefix for config properties
 */
public int ddl;

public FloodS(String prefix) { super(prefix); ddl=0;}


//--------------------------------------------------------------------------
// methods
//--------------------------------------------------------------------------

/**
 * This is the standard method the define periodic activity.
 * The frequency of execution of this method is defined by a
 * {@link peersim.edsim.CDScheduler} component in the configuration.
 */
public void nextCycle( Node node, int pid )
{
	Linkable linkable = 
		(Linkable) node.getProtocol( FastConfig.getLinkable(pid) );
	if (linkable.degree() > 0)
	{
		Node peern = linkable.getNeighbor(
				CommonState.r.nextInt(linkable.degree()));
		
		// XXX quick and dirty handling of failures
		// (message would be lost anyway, we save time)
		if(!peern.isUp()) return;
		
		((Transport)node.getProtocol(FastConfig.getTransport(pid))).
			send(
				node,
				peern,
				new FloodMessage(value,ddl,node,node,String.valueOf(node.getID())),
				pid);
		System.out.println("Start "+node.getID()+" ->"+peern.getID());
	}
}

//--------------------------------------------------------------------------

/**
* This is the standard method to define to process incoming messages.
*/
public void processEvent( Node node, int pid, Object event ) {
		
	FloodMessage aem = (FloodMessage)event;
	  
	if(aem.value==this.value)
	{
	  
		System.out.println("Sucess  at time: "+CommonState.getTime()+" ddl="+aem.ddl+", path is: "+aem.path+"->"+node.getID()
				+"  search value:"+aem.value+" this node'value is:"+value);
	}
	else
	{
		 aem.ddl++;
		if(aem.ddl<=8)
		{
			System.out.println("Forward at time: "+CommonState.getTime()+" ddl="+aem.ddl+", path is: "+aem.path+"->"+node.getID()
					+"  search value:"+aem.value+" this node'value is:"+value);
			Linkable linkable = 
				(Linkable) node.getProtocol( FastConfig.getLinkable(pid) );
	
			if (linkable.degree() > 0)
			{
				for(int i=0;i<linkable.degree();i++)
				{
									
					Node peern = linkable.getNeighbor(i);
					if(peern.getID()!=aem.sender.getID()&&peern.getID()!=aem.directsender.getID())//��ԭʼ��sender����һ��ת���ڵ�
					{
						// XXX quick and dirty handling of failures
						// (message would be lost anyway, we save time)
						if(!peern.isUp()) return;
						
						((Transport)node.getProtocol(FastConfig.getTransport(pid))).
							send(
								node,
								peern,
								new FloodMessage(aem.value,aem.ddl,aem.sender,node,aem.path+"->"+String.valueOf(node.getID())),
								pid);
					}
				
				
				}
			}
		}
		else
			
			System.out.println("Failed  at time: "+CommonState.getTime()+" ddl="+aem.ddl+", path is: "+aem.path+"->"+node.getID()
					+"  search value:"+aem.value+" this node'value is:"+value);
	}
	/*
	if( aem.sender!=null )
		((Transport)node.getProtocol(FastConfig.getTransport(pid))).
			send(
				node,
				aem.sender,
				new AverageMessage(value,null),
				pid);
				
	value = (value + aem.value) / 2;
	*/
}

}

//--------------------------------------------------------------------------
//--------------------------------------------------------------------------

/**
* The type of a message. It contains a value of type double and the
* sender node of type {@link peersim.core.Node}.
*/
class FloodMessage {

	final double value;
	/** If not null,
	this has to be answered, otherwise this is the answer. */
	final Node sender;
	final Node directsender;
	int ddl;
	String path;
	public FloodMessage( double value,int ddl, Node sender,Node directsender,String path )
	{
		this.value = value;
		this.sender = sender;
		this.directsender=directsender;
		this.ddl=ddl;
		this.path=path;
		
	}
}

