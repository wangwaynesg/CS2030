/open ImmutableList.java

ImmutableList<Integer> list = new ImmutableList<Integer>(1,2,3)
list.equals(list)
list.equals(Arrays.asList(1,2,3))
list.equals(new ImmutableList<Integer>(1,2,3))
list.equals(new ImmutableList<Integer>(1,2))
list.equals(new ImmutableList<Integer>(1,2,3,3))
list.equals(new ImmutableList<Integer>(3,2,1))
list.sorted(new Comparator<Integer>() {
    public int compare(Integer i1, Integer i2) {
        return i2 - i1;
    }})
list
try {
  new ImmutableList<Integer>(1,2,3).sorted(null);
} catch (NullPointerException e) {
  System.out.println(e);
}
/exit
