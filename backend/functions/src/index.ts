// // Start writing Firebase Functions
// // https://firebase.google.com/docs/functions/typescript
//
// export const helloWorld = functions.https.onRequest((request, response) => {
//   functions.logger.info("Hello logs!", {structuredData: true});
//   response.send("Hello from Firebase!");
// });
import "source-map-support/register";
import {https} from "firebase-functions";
import express from "express";
// import cors from 'cors';
import {v4 as uuid} from "uuid";

const app = express();

// app.use(authMiddleware)

app.get("/:id/question", (request, response) => {
  // Sanitize input

  // Retrieve the current question for the quiz.
  // let quizId = request.params.id // Use this

  response.send({
    id: uuid(),
    text: "How Evil Is Your Cat?",
    answerOptionType: "MultipleChoiceTextAnswerOption",
    answerOption: ["Very evil", "Covid 19 evil", "Cute"],
  });
});

exports.quiz = https.onRequest(app);
