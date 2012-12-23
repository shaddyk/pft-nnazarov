package com.example.tests;

import com.thoughtworks.xstream.XStream;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Author: Nikita Nazarov
 * Date: 23.12.12
 */
public class GroupObjectGenerator extends BaseObjectGenerator {

    public static void main(String[] args) throws IOException {
        if(args.length < 3) {
            System.out.println("Please specify parameters: <amount of test date>,<file>,<format>");
            return;
        }

        int amount = Integer.parseInt(args[0]);
        File file = new File(args[1]);

        if(file.exists()) {
            System.out.println("File exists, please remove it manually. Filename: " + file);
            return;
        }

        String format = args[2];

        List<GroupObject> groups = generateRandomGroups(amount);
        if(format.equals("csv")) {
            saveGroupsToCsv(groups,file);
        }
        else if(format.equals("xml")) {
            saveGroupsToXml(groups,file);
        }
        else {
            System.out.println("Unknown format: " + format);
        }
    }

    private static void saveGroupsToXml(List<GroupObject> groups, File file) throws IOException {
        XStream xStream = new XStream();
        xStream.alias("group",GroupObject.class);
        String xml = xStream.toXML(groups);
        FileWriter writer = new FileWriter(file);
        writer.write(xml);
        writer.close();
    }

    public static List<GroupObject> loadGroupsFromXmlFile(File file) throws IOException {
        XStream xStream = new XStream();
        xStream.alias("group",GroupObject.class);
        return (List<GroupObject>) xStream.fromXML(file);
    }

    private static void saveGroupsToCsv(List<GroupObject> groups, File file) throws IOException{
        FileWriter writer = new FileWriter(file);
        for (GroupObject group : groups) {
            writer.write(group.getName() + "," + group.getFooter() + "," + group.getHeader() + ",!\n");
        }

        writer.close();
    }

    public static List<GroupObject> loadGroupsFromCsvFile(String filename) throws IOException {
        List<GroupObject> list = new ArrayList<GroupObject>();
        BufferedReader br = new BufferedReader(new FileReader(filename));
        String line = br.readLine();
        while (line != null) {
            String[] parts = line.split(",");
            list.add(new GroupObject().withName(parts[0])
                    .withFooter(parts[1])
                    .withHeader(parts[2]));
            line = br.readLine();
        }

        br.close();
        return list;
    }

    public static List<GroupObject> generateRandomGroups(int amount) {
        List<GroupObject> list = new ArrayList<GroupObject>();

        for(int i = 0; i < amount; i++) {
            GroupObject groupObject = new GroupObject()
                    .withName(generateRandomString())
                    .withFooter(generateRandomString())
                    .withHeader(generateRandomString());
            list.add(groupObject);
        }
        return list;
    }

    protected static void saveContactsToXml(List<ContactObject> contacts, File file) throws IOException {
        XStream xStream = new XStream();
        xStream.alias("contact",ContactObject.class);
        String xml = xStream.toXML(contacts);
        FileWriter writer = new FileWriter(file);
        writer.write(xml);
        writer.close();
    }

    protected static void saveContactsToCsv(List<ContactObject> contacts, File file) throws IOException{
        FileWriter writer = new FileWriter(file);
        for (ContactObject contact : contacts) {
            writer.write(contact.getLastname() + "," + contact.getFirstname() + "," + contact.getPhone() + ",!\n");
        }

        writer.close();
    }

}
