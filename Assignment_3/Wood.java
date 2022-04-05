public enum Wood {

  C("C"),
  O("O");

  private String wood;

  Wood(String wood){
    this.wood = wood;
  }

  public String getWood(){
    if(wood.equals("C"))
        return "Cherry Maple";
    return "Oak";
  }

  // public void setWood() {
  //   this.wood = wood;
  // }
}
