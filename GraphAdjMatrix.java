import java.util.*;

public class GraphAdjMatrix implements Graph{
    int thevertices;
    int theedges;
    private ArrayList<Stuff> bunchofthings;

public GraphAdjMatrix(int incomingvalue)
        {
            this.thevertices = incomingvalue;
            // this makes the verticies the value coming in as an arugment
            this.theedges = 0;
            //declares the edges as zero
            this.bunchofthings = new ArrayList<>();
            //makes the new array list
        }

        class Stuff implements Comparable<Stuff>
        {
            // the stuff class just keeps 3 items
            // makes it easier to manage the data.
            int item1, item2, item3;

            private Stuff(int thing1, int thing2, int thing3) {
                // keeping the three different things
                item1 = thing1;
                item2 = thing2;
                item3 = thing3;
            }

            @Override
            public int compareTo(Stuff o) {
                // typical compare to for the collections
                if (this.item3 < o.item3)
                    //if it is less than it returns -1
                    return -1;
                else if (this.item3 > o.item3)
                    // if it is greater than then it returns 1
                    return 1;
                return 0;
            }

        }

        @Override
        public int getEdge(int v1, int v2) {
            try {
                for (Stuff t : bunchofthings)
                // goes through the array list
                {
                    if (t.item1 == v1 && t.item2 == v2)
                    // checks to see if the values match the ones located in the specific object
                    {
                        return t.item3;
                        // returns the weight in that specific object
                    }
                }
            } catch (IndexOutOfBoundsException e)
            {
            }
            return 0;
        }

        @Override
        public int createSpanningTree()
        {
            ArrayList<Stuff> newlist = new ArrayList<>(bunchofthings);
            // creates a newlist with the copy of the adjmatrix stuff
            Collections.sort(newlist);
            //sorts out the new list
            bunchofthings.clear();
            //clears out the old one so that we will later
            //on dont need

            int dJSET[] = new int[thevertices];
            // this creates a disjoint set array with the sizes of the verticies
            cDJS(dJSET);
            // this calls the disjoint set function witt the new array as an argument.
            int olderone = theedges;
            //keeps the old number of edges
            theedges = 0;
            //makes the edges now become zero
            for(int i = 0; i < olderone; i++)
            {
                //start from the least to the greatest
                Stuff k = newlist.remove(0);
                int i1 = fDJSET(dJSET,k.item1);
                // gets the arraylist values and then find the parent
                int i2 = fDJSET(dJSET,k.item2);
                // does the same thing again

                //compare if the two things are part of the same union
                //then add them
                if (i1 != i2){
                    bunchofthings.add(k);
                    // add them to the list
                    theedges++;
                    // increment the edges
                    uDJSET(dJSET,i1,i2);
                    // unionize them.
                }
            }

            int totalWeight = 0;
            // this initializes a variable to keep track of the weight recorded in the arraylist
            for (Stuff e: bunchofthings) {
                // iteratring and adding through the loop
                totalWeight+=e.item3;
            }
            // returning the value so that it can see if the total weight would be correct
            return totalWeight;
        }

        @Override
        public void addEdge(int v1, int v2)
        {
            // calls the add edge function with zero weight
            addEdge(v1, v2, 0);
        }

        @Override
        public void topologicalSort()
        {
            // no need for this function
        }

        public void cDJS(int [] theparent)
        {
            // this creates and initializes the disjoint set with the values being -1.
            for(int i = 0; i < thevertices; i++)
            {
                theparent[i] = -1;

            }
            // disjoints work where they each they claim to be their own parent
            // thats why need to use I.
        }

        public int fDJSET(int [] theparent, int vertex1)
        {
            // recursive function that goes through and finds the parent of the disjoint set
            if (theparent[vertex1] != -1)
            {
                //
                return fDJSET(theparent,theparent[vertex1]);
            }
            return vertex1;
        }

        public void uDJSET(int [] theparent, int vertex1, int vertex2)
                // this function finds the parent and the unionizes the things in the disjoint set.
        {
            int sp1 = fDJSET(theparent,vertex1);
            // finds the parent
            int sp2 = fDJSET(theparent,vertex2);
            // unions the parent of two to one and compares to see if they are in the same union
            if (sp1<sp2)
            {
                // find the one that has the less value, becomes the parent
                theparent[sp2] = sp1;
            }
            else
            {
                theparent[sp1] = sp2;
            }
        }

        @Override
        public void addEdge(int v1, int v2, int weight) {
            try {
                // creates a new object with the verticies and the weight
                Stuff newstuff = new Stuff(v1, v2, weight);
                // adds it to the arraylist
                bunchofthings.add(newstuff);
                // indexes the number of edges.
                theedges++;
            }
            catch (IndexOutOfBoundsException e)
            {

            }
        }
    }