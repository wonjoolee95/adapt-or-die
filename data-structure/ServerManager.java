/*

Write a ServerManager which manages allocating and deallocating servers. It should allocate the lowest available
server number. Take a look at the sample interactions below:

allocate("apibox") -> "apibox1"
allocate("apibox") -> "apibox2"
allocate("apibox") -> "apibox3"
allocate("sitebox") -> "sitebox"
deallocate("apibox2") -> true
deallocate("apibox2") -> false
allocate("apibox") -> "apibox2"
allocate("apibox") -> "apibox4"

*/

public class ServerManager {

    private HashMap<String, HashSet<Integer>> servers;
    private HashMap<String, LinkedList<Integer>> reusableServers;

    public ServerManager() {
        this.servers = new HashMap<>();
        this.reusableServers = new HashMap<>();
    }

    public String allocate(String hostType) {
        if (!servers.containsKey(hostType)) {
            servers.put(hostType, new HashSet<>());
            reusableServers.put(hostType, new LinkedList<>());
        }

        int availableHostNumber;
        if (reusableServers.get(hostType).isEmpty()) {
            availableHostNumber = servers.get(hostType).size() + 1;
        } else {
            availableHostNumber = reusableServers.get(hostType).removeFirst();
        }

        // Add hostNumber to hostType.
        servers.get(hostType).add(availableHostNumber);

        return hostType + Integer.toString(availableHostNumber);
    }

    public boolean deallocate(String hostName) {
        Pair<String, Integer> parsedHostName = parseHostName(hostName);
        String hostType = parsedHostName.getKey();
        int hostNumber = parsedHostName.getValue();

        // Verify hostType and hostNumber exists.
        if (!servers.containsKey(hostType) || !servers.get(hostType).contains(hostNumber)) {
            return false;
        }

        // Remove hotsNumber from hostType.
        servers.get(hostType).remove(hostNumber);

        int index = 0;
        for (Integer num : reusableServers.get(hostType)) {
            if (num < hostNumber) {
                break;
            }
            index++;
        }

        // Add the hostNumber to the correct posotion in the reusableServers linkedlist of hostType.
        reusableServers.get(hostType).add(index, hostNumber);

        return true;
    }

    private Pair<String, Integer> parseHostName(String hostName) {
        StringBuilder hostType = new StringBuilder();
        StringBuilder hostNumber = new StringBuilder();

        for (char c : hostName.toCharArray()) {
            if (Character.isLetter(c)) {
                hostType.append(c);
            } else {
                hostNumber.append(c);
            }
        }

        return new Pair<>(hostType.toString(), Integer.parseInt(hostNumber.toString()));
    }
}

/*

Time:
  allocate - O(1).
  deallocate - O(n). 
Space: O(n).

There are many ways to implement this data structure. Here we try to optimize allocate operation in some cost to space 
and deallocate operation. 

Note that there are three distinct variables in this question -- hostType, hostNumber, and hostName. We can see that
hostType concatenated with hostNumber gives us hostName. In the hostname "apibox2", "apibox" is the hostType and
"2" is hostNumber.

A brute-force approach would be a keep a map of hostName to a list of allocated hostNumbers. In this implementation,
both allocate and deallocate operations would be O(n) time because we need look for which hostNumber can be allocated.
We need to iterate through the allocated hostNumbers, and find the minium available hostNumber. 

How can we reduce this runtime? What if we have a value that stores the next available hostNumber? If we have this value,
allocation operation would be a simple O(1) operation. To do this, we keep another map of hostName to a linked list of
next available hostNumber.

*/
