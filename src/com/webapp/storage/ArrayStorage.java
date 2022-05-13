package com.webapp.storage;

import com.webapp.model.Resume;

import java.util.Arrays;
import java.util.Objects;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    private Resume[] storage = new Resume[10000];
    private static int size = 0;

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    void update(Resume r) {
        int index = getIndex(r.getUuid());
        if (index == -1) {
            System.out.println("com.webapp.model.Resume " + r.getUuid() + " is not exist");
        } else {
            storage[index] = r;
        }
    }

    public void save(Resume r) {
        if (getIndex(r.getUuid()) != -1) {
            System.out.println("com.webapp.model.Resume " + r.getUuid() + " is already exist");
        } else if (size == storage.length) {
            System.out.println("The storage of resumes is overflow");
        } else {
            storage[size] = r;
            size++;
        }
    }

    public Resume get(String uuid) {
        int index = getIndex(uuid);
        if (index == -1) {
            System.out.println("com.webapp.model.Resume " + uuid + " is not exist");
            return null;
        }
        return storage[index];
    }

    public void delete(String uuid) {
        int index = getIndex(uuid);
        if (index == -1) {
            System.out.println("com.webapp.model.Resume " + uuid + "is not exist");
        } else {
            storage[index] = storage[size - 1];
            storage[size - 1] = null;
            size--;
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        return  Arrays.copyOfRange(storage,0,size);
    }

    public int size() {
        return size;
    }

    private int getIndex(String uuid) {

        for (int i = 0; i < size; i++) {
            if (Objects.equals(uuid, storage[i].getUuid())) {
                return i;
            }
        }
        return -1;
    }
}
