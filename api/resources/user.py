from flask_restful import Resource

# users = [{'name': 'mark'}]

class User(Resource):
    def get(self):
        return "Hihihihihihi"
        # if name in users.values():
        # return {
        #     'message': 'good',
        #     'user': 'mark'
        # }
        # else:
        #     return {
        #         'message': 'username not exist!'
        #     }
    def post(self, name):
        pass
    def put(self, name):
        pass
    def delete(self, name):
        pass
