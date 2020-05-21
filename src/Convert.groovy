static void Convert(String filePath) {

    new File('test.txt') << 'test'

    final def dictionaryLead = "Java8to11"

    final def dictionaryTail = "Dictionary.txt"

    def dictionaries = [:]

    def fileRoot = new File(filePath);

    def backupFile, fileText;

    def exts = [".java", ".properties", ".gradle"]

    def dict = [:]

    def dictRoot = new File("src/dictionaries")

    dictRoot.eachFileRecurse(
            { file ->
                def fileExt = "." + (file.name[dictionaryLead.length()..-dictionaryTail.length() - 1]).toLowerCase()
                dictionaries[fileExt] = dict
                file.eachLine { line ->
                    def a = line.split(" ")[0]
                    def b = line.split(" ")[1]
                    dict.put(a, b)
                }
            })

    fileRoot.eachFileRecurse(
            { file ->
                for (ext in exts) {
                    if (file.name.endsWith(ext)) {
                        fileText = file.text
                        backupFile = new File(file.path + ".bak")
                        backupFile.write(fileText)
                        dictionaries.get(ext).each { entry ->
                            fileText = fileText.replaceAll(entry.key, entry.value)
                        }
                        file.write(fileText)
                    }
                }
            }
    )
}


//C:/devl/workspace/Java8Conversion/wordyninjablog