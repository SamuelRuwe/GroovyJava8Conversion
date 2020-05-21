class Main {

    static final def dictionaryLead = "Java8to11"

    static final def dictionaryTail = "Dictionary.txt"

    static def dictionaries = [:]

    static def dictRoot = "C:/devl/workspace/Java8Conversion/Groovy/src/dictionaries"

    static void main(String[] args) {

        def fileRoot = new File("C:/devl/workspace/Java8Conversion/wordyninjablog");

        def backupFile, fileText;

        def exts = [".java", ".properties", ".gradle"]

        new File(dictRoot).eachFileRecurse(
                file -> {
                    def dict = [:]
                    def fileExt = "." + (file.name[dictionaryLead.length()..-dictionaryTail.length() - 1]).toLowerCase()
                    dictionaries[fileExt] = dict
                    file.eachLine { line ->
                        def a = line.split(" ")[0]
                        def b = line.split(" ")[1]
                        dict.put(a, b)
                    }
                })
        println dictionaries.get('.gradle')

        fileRoot.eachFileRecurse(
                { file ->
                    for (ext in exts) {
                        if (file.name.endsWith(ext)) {
                            println file.name
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
}