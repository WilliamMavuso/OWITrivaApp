package com.example.owitrivia


class Questions {
    private val mQuestions = arrayOf(
        "Who is this American rapper, singer, songwriter, and record producer?",
        "Highest paid 2019 tennis player:",
        "Portuguese professional footballer:",
        "Who is \"it\"? (Check all apply)",
        "Multi-platinum selling American artist:",
        "45th president of the United States:",
        "Canadian rapper, singer, songwriter, executive producer, actor, and entrepreneur:",
        "Who is he? (All can apply)",
        "5 time Ballon d'or winner:",
        "South African international award winning artist:"
    )
    private val mChoice = arrayOf(
        arrayOf("Travis Scott", "Drake", "Sonde"),
        arrayOf("Nadal", "Dzokovic", "Federrer"),
        arrayOf(""),
        arrayOf("Stoney", "Livingsone", "Waters"),
        arrayOf("Jcole", "Bas", "Shane Eagle"),
        arrayOf("Jeff Dunham", "Jeff Bezos", "Donald Trump"),
        arrayOf("Mark Zuckerberg", "Drake", "Dwayne Johnson"),
        arrayOf("Ramaphosa", "Zuma", "Mbeki"),
        arrayOf("Messi", "Ronaldo", "Rooney"),
        arrayOf("Sho Madjozi", "Gigi Lamaine", "Fifi Cooper")
    )
    private val mImages = arrayOf(
        "travis",
        "nadal",
        "ronaldo",
        "stoney",
        "jcole",
        "trump",
        "drakee",
        "zuma",
        "messi",
        "madjozi"
    )
    private val mQuestionType = arrayOf(
        "radiobutton",
        "radiobutton",
        "edittext",
        "checkbox",
        "radiobutton",
        "radiobutton",
        "radiobutton",
        "checkbox",
        "radiobutton",
        "radiobutton"
    )
    private val mMaxAnswer = intArrayOf(
        1,
        1,
        1,
        3,
        1,
        1,
        1,
        2,
        1,
        1
    )
    private val mCorrectAnswer = arrayOf(
        "Travis Scott",
        "Nadal",
        "Gates",
        "Stoney,Livingsone,Waters",
        "Jcole",
        "Donald Trump",
        "Drake",
        "Zuma",
        "Messi",
        "Sho Madjozi"
    )

    fun getQuestion(q: Int): String {
        return mQuestions[q]
    }

    fun getChoice(q: Int): Array<String> {
        return mChoice[q]
    }

    fun getImage(q: Int): String {
        return mImages[q]
    }

    fun getType(q: Int): String {
        return mQuestionType[q]
    }

    fun getMaxAnswer(q: Int): Int {
        return mMaxAnswer[q]
    }

    val length: Int
        get() = mQuestions.size

    fun getCorrectAnswer(q: Int): String {
        return mCorrectAnswer[q]
    }
}