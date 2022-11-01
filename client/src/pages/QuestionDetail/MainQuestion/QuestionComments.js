import Comments from '../Comments/Comments';

// const stupData = [
//   {
//     commentId: 1,
//     memberResponseDto: {
//       memberId: 1,
//       nickName: 'Tagir Valeev',
//       email: 'abc@email.com',
//     },
//     createdAt: '2022-10-28T18:37:47.0798803',
//     body: 'possible duplicate of Collect stream with grouping, counting and filtering operations',
//     commentType: 'QUESTION',
//   },
//   {
//     commentId: 2,
//     memberResponseDto: {
//       memberId: 2,
//       nickName: 'Ravn Andersen',
//       email: 'abc@email.com',
//     },
//     body: `If you don't want to collect the stream, this essentially boils down to "how can I look at more than one item at once in a stream"?`,
//     createdAt: '2022-10-31T09:47:42.482746',
//     commentType: 'QUESTION',
//   },
//   {
//     commentId: 3,
//     memberResponseDto: {
//       memberId: 3,
//       nickName: 'Saroj Kumar Sahoo',
//       email: 'abc@email.com',
//     },
//     body: `Set<Integer> items = new HashSet(); numbers.stream().filter(n -> i!tems.add(n)).collect(Collectors.toSet()); `,
//     createdAt: '2022-10-28T13:12:48.456698',
//     commentType: 'QUESTION',
//   },
// ];
const QuestionComments = ({ data }) => {
  const commentsData = data === undefined ? [] : data.comments;
  return <Comments data={commentsData}></Comments>;
};

export default QuestionComments;
