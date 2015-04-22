package org.drools.core.phreak;

import org.drools.core.common.InternalWorkingMemory;

public class SynchronizedPropagationList implements PropagationList {
    private volatile PropagationEntry head;
    private volatile PropagationEntry tail;

    @Override
    public synchronized void addEntry(PropagationEntry entry) {
        if (head == null) {
            head = entry;
            tail = entry;
        } else {
            tail.setNext(entry);
            tail = entry;
        }
    }

    @Override
    public void flush(InternalWorkingMemory workingMemory) {
        PropagationEntry currentHead;
        synchronized (this) {
            currentHead = head;
            head = null;
            tail = null;
        }
        for (PropagationEntry entry = currentHead; entry != null; entry = entry.getNext()) {
            entry.execute(workingMemory);
        }
    }

    @Override
    public synchronized void reset() {
        head = null;
        tail = null;
    }
}
