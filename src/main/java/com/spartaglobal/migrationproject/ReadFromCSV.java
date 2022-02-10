{
    public static ArrayList<String[]> read(String csv) {
        ArrayList<String[]> data = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(csv))) {
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(Arrays.toString(line.split(",")));
                String[] lines = (line.split(","));
                data.add(lines);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }
    public static int duplicateCheck(ArrayList<String[]> data){
        int duplicates = 0;
        List<String> dupids = new ArrayList<>();
        List<String> safe = new ArrayList<>();
        for(String[] i : data) {
            String id = i[0];
            if(id.equals((String)i[0]) && i[1].length() <= 3 && i[2].equals((String)i[2]) && i[3].length() == 1 && i[4].equals((String)i[4])
                    && i[5].length() == 1 && i[6].contains("@") && i[7].contains("/") && i[8].equals((String) i[8])){
                safe.add(id);
                continue;
            }
            int f = 0;
            if (dupids.contains(id)) {
                continue;
            }
            for (String[] x : data) {
                if (x[0].equals(id)) {
                    f++;
                    if (f > 1 && dupids.contains(id) == false) {
                        dupids.add(id);
                        duplicates++;
                    }
                }
            }
        }
        System.out.println("number of Duplicates: "+duplicates);
        System.out.println("dup"+dupids);
        System.out.println("safe"+safe);
        return duplicates;
    }
}
