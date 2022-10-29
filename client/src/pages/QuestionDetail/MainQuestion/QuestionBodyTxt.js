import styled from 'styled-components';

const QuestionBodyTxtContainer = styled.div`
  width: 100%;
  text-align: left;
  font-size: 15px;
  overflow-wrap: break-word;
  vertical-align: baseline;
  line-height: 22.5px;
  color: #232629;
`;

//여기서 axios 요청(아마?)
const body = `I am trying to list out duplicate elements in the integer list say for eg,

List<Integer> numbers = Arrays.asList(new Integer[]{1,2,1,3,4,4});    
using Streams of jdk 8. Has anybody tried out. To remove the duplicates we can use the distinct() api. But what about finding the duplicated elements ? Anybody can help me out ?`;
const QuestionBodyTxt = () => {
  return <QuestionBodyTxtContainer>{body}</QuestionBodyTxtContainer>;
};

export default QuestionBodyTxt;
