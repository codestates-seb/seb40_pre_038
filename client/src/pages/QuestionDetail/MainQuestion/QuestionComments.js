import Comments from '../Comments/Comments';

const QuestionComments = ({ data, type }) => {
  const commentsData =
    Object.keys(data).length === 0 ? [] : data.commentsWithUser;
  return <Comments data={commentsData} type={type}></Comments>;
};

export default QuestionComments;
