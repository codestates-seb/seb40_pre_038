import {
  GET_ANSWERS,
  ADD_ANSWER,
  POST_ANSWER_COMMENT,
  DELETE_ANSWER,
  DELETE_ANSWER_COMMENT,
} from '../_actions/answer_action';

const initialState = {
  data: [],
};
export const answerReducer = (state = initialState, action) => {
  let changeAnswer, changeComments, newComments;
  switch (action.type) {
    case GET_ANSWERS:
      return { ...state, ...action.payload };
    case ADD_ANSWER:
      return {
        ...state,
        data: [...state.data, action.payload.data],
      };
    case DELETE_ANSWER:
      return {
        ...state,
        data: [
          ...state.data.filter((el) => {
            return el.answerId !== action.payload.answer_id;
          }),
        ],
      };
    case POST_ANSWER_COMMENT:
      changeAnswer = state.data.filter((el) => {
        return el.answerId === action.payload.answer_id;
      });
      changeAnswer[0].commentsWithUser.push(action.payload.data.data);
      return {
        ...state,
        data: [
          ...state.data.map((el) => {
            if (el.answerId === action.payload.answer_id)
              return changeAnswer[0];
            else return el;
          }),
        ],
      };
    case DELETE_ANSWER_COMMENT:
      changeComments = state.data.filter((el) => {
        return el.answerId === action.payload.answerId;
      })[0].commentsWithUser;
      newComments = changeComments.filter((el) => {
        return el.commentId !== action.payload.commentId;
      });
      return {
        ...state,
        data: [
          ...state.data.map((el) => {
            if (el.answerId === action.payload.answerId)
              return { ...el, commentsWithUser: newComments };
            else return el;
          }),
        ],
      };
    default:
      return state;
  }
};
