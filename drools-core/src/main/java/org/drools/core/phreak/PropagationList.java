package org.drools.core.phreak;

import org.drools.core.common.InternalWorkingMemory;

public interface PropagationList {
    void addEntry(PropagationEntry propagationEntry);

    void flush(InternalWorkingMemory workingMemory);

    public void reset();
}
