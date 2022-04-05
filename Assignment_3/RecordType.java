public enum RecordType {
  R("R"),
  S("S"),
  P("P");

  private String recordType;

  RecordType(String recordType){
    this.recordType = recordType;
  }

  public String getRecordType(){
    return recordType;
  }

  public void setRecordType(String recordType) {
    this.recordType = recordType;
  }
}
