package com.example.mockserver.responses

/**
 * Mocked responses.
 */
object Repos {
    fun listOfRepos() : String {
        return "[\n" +
                "  {\n" +
                "    \"id\": 18221276,\n" +
                "    \"name\": \"git-consortium\",\n" +
                "    \"stargazers_count\": 7\n" +
                "  },{\n" +
                "    \"id\": 18221277,\n" +
                "    \"name\": \"Mock Server\",\n" +
                "    \"stargazers_count\": 1\n" +
                "  }\n" +
                "]"
    }
}