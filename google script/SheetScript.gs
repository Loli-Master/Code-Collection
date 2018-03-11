function onOpen(){
  var spreadsheet = SpreadsheetApp.getActive();
  var masukNilai = [
    {name: 'Input Soal Solved Terbaru', functionName: 'MasukNilai'},
    {name: 'Hapus Data Solved Terbaru', functionName: 'HapusNilai'}
  ];
  spreadsheet.addMenu('Input', masukNilai);
}

function MasukNilai(){
  var name = Browser.inputBox('Nama:', Browser.Buttons.OK_CANCEL);
  if (name == 'cancel')return;
  var sheet = SpreadsheetApp.getActive().getSheetByName(name);
  if (sheet == null){
    Browser.msgBox('Error!', 'Nama yang dimasukkan tidak valid', Browser.Buttons.OK);
    return;
  }
  Logger.log(sheet.getIndex());
  var NOSRange = sheet.getRange(1, 2);
  var numOfSub = Number(NOSRange.getValue());
  Browser.msgBox('Konfirmasi', 'Total submisi sebelumnya: ' + numOfSub + ', total submisi sekarang: ' + (numOfSub+1) , Browser.Buttons.OK);
  numOfSub++;
  NOSRange.setValue(numOfSub);
  var probCode = Browser.inputBox('Kode Soal','Format <CF/UVA><KODE SOAL> (CF1A ,UVA1000) :', Browser.Buttons.OK_CANCEL);
  var probTag = Browser.inputBox('Tag','Tag yang paling sesuai buat problem ini :', Browser.Buttons.OK_CANCEL);
  var newData = sheet.getRange(6 + numOfSub, 1, 1, 3);
  var newInput = [
    [numOfSub, probCode, probTag]
  ];
  newData.setValues(newInput);
}

function HapusNilai(){
  var name = Browser.inputBox('Nama:', Browser.Buttons.OK_CANCEL);
  if (name == 'cancel')return;
  var sheet = SpreadsheetApp.getActive().getSheetByName(name);
  if (sheet == null){
    Browser.msgBox('Error!', 'Nama yang dimasukkan tidak valid', Browser.Buttons.OK);
    return;
  }
  Logger.log(sheet.getIndex());
  var NOSRange = sheet.getRange(1, 2);
  var numOfSub = Number(NOSRange.getValue());
  Browser.msgBox('Konfirmasi', 'Total submisi sebelumnya: ' + numOfSub + ', total submisi sekarang: ' + (numOfSub-1) , Browser.Buttons.OK);
  numOfSub--;
  NOSRange.setValue(numOfSub);
  var newData = sheet.getRange(7 + numOfSub, 1, 1, 3);
  newData.clearContent();  
}
