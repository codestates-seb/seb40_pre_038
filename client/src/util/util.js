import md5 from 'md5';

const getRandomNumber = (min = 1, max = 98) => {
  return parseInt(Math.random() * (Number(max) - Number(min) + 2));
};

export const getRandomUserImgUrl = (email) => {
  return Math.floor(Math.random() * 10) % 2
    ? `http://gravatar.com/avatar/${md5(email.toLowerCase())}?d=identicon`
    : `https://randomuser.me/api/portraits/women/${getRandomNumber()}.jpg`;
};
