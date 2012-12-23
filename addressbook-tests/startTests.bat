REM ѕараметризую название конфига, по аналогии можно сделать дл€ тест-сьюта
REM ƒл€ нагл€дности сделал ручной ввод, на билд-агенте можно выт€гивать из системных свойств
echo "Enter configFile name"
set /p configFile=
REM —ам запуск тестов
java -cp C:\pft-nnazarov\selenium-server-standalone-2.27.0.jar;C:\pft-nnazarov\lib\xstream\xmlpull-1.1.3.1.jar;C:\pft-nnazarov\lib\xstream\xpp3_min-1.1.4c.jar;C:\pft-nnazarov\lib\xstream-1.4.3.jar;C:\pft-nnazarov\addressbook-tests\out\production\addressbook-tests -DconfigFile=%configFile% org.testng.TestNG testng-customsuite.xml